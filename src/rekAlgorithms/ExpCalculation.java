package rekAlgorithms;
import dao.*;
import dbProcess.MySQLAccess;

import java.sql.*;

public class ExpCalculation {
	
	public static void expMain(int src_id, int des_id){
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		MySQLAccess db = new MySQLAccess();		
		try {
			connect = db.connect();
			Statement st1 = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
			
			Statement st2 = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
			
			Statement st3 = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
			
			// Get the number of entities in the entity table
			ResultSet int_rs = st1.executeQuery("SELECT * FROM interaction WHERE source_id = " + src_id + " AND destination_id = " + des_id + " ORDER BY interaction_timestamp ASC");
			Experience exp_obj = new Experience();
			
			if (int_rs.next()){
				ResultSet init_test = st2.executeQuery("SELECT * FROM experience WHERE source_id = " + src_id + " AND destination_id = " + des_id);
				if (!init_test.next()){ //meaning there is no experience has been calculated between src and des
					//initial experience object as default					
					exp_obj.setSource_id(src_id);
					exp_obj.setDestination_id(des_id);
					exp_obj.setExperience_timestamp(int_rs.getTimestamp("interaction_timestamp"));
					exp_obj.setExperience_value(0.3);
					exp_obj.setPrev_value(0.3);
					
					// update experience					
					exp_obj = expUpdate(exp_obj, int_rs.getDouble("interaction_value"), int_rs.getTimestamp("interaction_timestamp"));
					
					preparedStatement = connect.prepareStatement("INSERT INTO  experience (source_id, destination_id, experience_value, prev_value, experience_timestamp) VALUES (?, ?, ?, ?, ?)");
			        preparedStatement.setInt(1, src_id);
			        preparedStatement.setInt(2, des_id);
			        preparedStatement.setDouble(3, exp_obj.getExperience_value());
			        preparedStatement.setDouble(4, exp_obj.getPrev_value());
			        preparedStatement.setTimestamp(5, exp_obj.getExperience_timestamp());
			        preparedStatement.executeUpdate();
			        
			        System.out.println(exp_obj.getExperience_value());
			        System.out.println(int_rs.getDouble("interaction_value"));

				}
		        init_test.close();
		        st2.close();
			}
			//Get the updated exp_rs in case just created 
			ResultSet exp_rs = st3.executeQuery("SELECT * FROM experience WHERE source_id = " + src_id + " AND destination_id = " + des_id);
			exp_rs.next();
			do {
				exp_obj.setSource_id(src_id);
				exp_obj.setDestination_id(des_id);
				exp_obj.setExperience_timestamp(exp_rs.getTimestamp("experience_timestamp"));
				exp_obj.setExperience_value(exp_rs.getDouble("experience_value"));
				exp_obj.setPrev_value(exp_rs.getDouble("prev_value"));
				
				exp_obj = expUpdate(exp_obj, int_rs.getDouble("interaction_value"), int_rs.getTimestamp("interaction_timestamp"));
				
				preparedStatement = connect.prepareStatement("UPDATE experience SET experience_value = ?, prev_value = ?, experience_timestamp = ?");			        
		        preparedStatement.setDouble(1, exp_obj.getExperience_value());
		        preparedStatement.setDouble(2, exp_obj.getPrev_value());
		        preparedStatement.setTimestamp(3, exp_obj.getExperience_timestamp());
		        preparedStatement.executeUpdate();

		        System.out.println(int_rs.getDouble("interaction_value"));
		        System.out.println(exp_obj.getExperience_value());
		        
			} while (int_rs.next());
			
			int_rs.close();
			st1.close();
			exp_rs.close();
			st3.close();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		db.close();
	}
	
	//Read interaction table and create
	public static Experience expUpdate(Experience exp_obj, double inter_value, Timestamp inter_timestamp){
		
		//Experience Algorithm Parameters Settings
		int decay_period = 3; //set 3 months. If it is more than 3 month without interactions (or neutral interactions), then Experience decays
		double min_exp = 0; //set min experience
		double exp_init = 0.3;
		double unsup_thres = 0.3;
		double sup_thres = 0.6;
		double alpha = 0.1; //Maximum increase of Experience
		double beta = 2; //Experience loss rate
		double min_decay = 0.005;
		double decay_rate = 0.005;
		
		
		double exp_newvalue = 0;
		double exp_currentvalue = exp_obj.getExperience_value();
		double exp_prevalue = exp_obj.getPrev_value(); //used in case of decay		
		Timestamp exp_oldtsmt = exp_obj.getExperience_timestamp();
		
		if (exp_oldtsmt.before(inter_timestamp) || exp_oldtsmt.equals(inter_timestamp)) {
			// Experience Update
			System.out.println("Start of Experience Update");
			
			exp_obj.setExperience_timestamp(inter_timestamp); //update timestamp
			
			if ((unsup_thres <= inter_value) && (inter_value <= sup_thres)){
				// Decay function:  exp(j+1) = max(exp_init, exp(j) - min_decay*(1 + decay_rate - exp(j-1)));
				int timediff = ComFunc.timediff(inter_timestamp, exp_oldtsmt) ; // time difference in months
				int times = timediff/decay_period;
				
				System.out.println("Experience Decay --- ");
				System.out.println(times);
				System.out.println("----decay---");
				
				if (times > 0){ //number of times for decay calculation
					for (int i = 1; i < times + 1; i++){
						exp_newvalue = Math.max(exp_init, exp_currentvalue - min_decay*(1 + decay_rate - exp_prevalue));
						exp_obj.setExperience_value(exp_newvalue);
						exp_obj.setPrev_value(exp_currentvalue);
					}
					
				} else {
					// do nothing
				}
				
			} else if (inter_value < unsup_thres) {
				// Loss function: exp(j+1) = max(min_exp, exp(j)-loss_rate*alpha*(1-exp(j)));
				exp_newvalue = Math.max(min_exp, exp_currentvalue - beta*alpha*(1-exp_currentvalue));
				exp_obj.setExperience_value(exp_newvalue);
				exp_obj.setPrev_value(exp_currentvalue);
				
				System.out.println("Experience Loss --- ");
				
			}
			else if(inter_value > sup_thres){
				// 	Development function: exp(i+1)= (1 - alpha)*exp(i) + alpha;
				exp_newvalue = (1 - alpha)*exp_currentvalue + alpha;
				exp_obj.setExperience_value(exp_newvalue);
				exp_obj.setPrev_value(exp_currentvalue);
				
				System.out.println("Experience Development --- ");

			}
		
		System.out.println("------End of Experience Update------------");
			
		} else {
			// Do nothing, there is something wrong
			return exp_obj;
		}
		return exp_obj;
	}
}