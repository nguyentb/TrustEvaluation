package dbProcess;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

import dao.*;

import java.util.Calendar;

public class InteractionCreate {
	
	/* Create M for Interactions for  N entities */
	public void interactioncreation(int M){
		PreparedStatement preparedStatement = null;
		Connection connect = null;		
		MySQLAccess db = new MySQLAccess();
		int N;
	    try {
	    	connect = db.connect();
	    	Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	    	ResultSet.CONCUR_UPDATABLE);
			// Get the number of entities in the entity table
			ResultSet rs = st.executeQuery("SELECT * FROM entity");	                
			// get the number of rows from the result set
			N =  resultSetCount(rs);
			System.out.println(N);
			rs.close();
			st.close();
			
	        preparedStatement = connect.prepareStatement("INSERT INTO  interaction (source_id, destination_id, interaction_value, interaction_timestamp) VALUES (?, ?, ?, ?)");
			
			for (int i=0; i<M; i++){
				Interaction inter = new Interaction();
				
				int randomSrc = ThreadLocalRandom.current().nextInt(1, N + 1);
				inter.setSource_id(randomSrc);				
				int randomDes = ThreadLocalRandom.current().nextInt(1, N + 1);			
				inter.setDestination_id(randomDes);
				inter.setInteraction_value(Math.random());
				
				double scale = (double)i/M;				
				//System.out.println(timeGenerator(2015, 2018, scale));
				inter.setInteraction_timestamp(timeGenerator(2015, 10, 1, 2017, 4, 16, scale));
				
				preparedStatement.setInt(1, inter.getSource_id());
		        preparedStatement.setInt(2, inter.getDestination_id());
		        preparedStatement.setDouble(3, inter.getInteraction_value());		        
		        preparedStatement.setTimestamp(4, inter.getInteraction_timestamp());
		        preparedStatement.executeUpdate();				
			}			
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    db.close();	
	}
	
	// Get the number of entities in the entity table
	private int resultSetCount(ResultSet resultSet) throws SQLException{
	    try{
	        int i = 0;
	        while (resultSet.next()) {
	            i++;
	        }
	        return i;
	    } catch (Exception e){
	       System.out.println("Error getting row count");
	       e.printStackTrace();
	    }
	    return 0;
	}	
	
	private Timestamp timeGenerator(int initialYear, int initialMonth, int initialDate, int lastYear, int lastMonth, int lastDate, double scale){
		if (initialYear > lastYear) {
	        int year = lastYear;
	        lastYear = initialYear;
	        initialYear = year;
	    }

	    Calendar cInitialYear = Calendar.getInstance();
	    cInitialYear.set(Calendar.YEAR, initialYear);
	    cInitialYear.set(Calendar.MONTH, initialMonth);
	    cInitialYear.set(Calendar.DATE, initialDate);
	    long offset = cInitialYear.getTimeInMillis();

	    Calendar cLastYear = Calendar.getInstance();
	    cLastYear.set(Calendar.YEAR, lastYear);
	    cLastYear.set(Calendar.MONTH, lastMonth);
	    cLastYear.set(Calendar.DATE, lastDate);
	    long end = cLastYear.getTimeInMillis();

	    long diff = end - offset + 1;
	    return new Timestamp(offset + (long) (scale * diff));
	}
}