package dbProcess;
import java.sql.*;

import dao.*;
import rekAlgorithms.StdRandom;

public class EntityCreate {
	/* Create n entities for trust evaluation */
	public void entitycreation(int n){
		PreparedStatement preparedStatement = null;
		Connection connect = null;
		
		MySQLAccess db = new MySQLAccess();
	    try {
	        connect = db.connect();
	        
	        // Statements allow to issue SQL queries to the database
	        preparedStatement = connect.prepareStatement("INSERT INTO  entity (entity_name, ability, integrity, benevolence, reputation, entity_timestamp) VALUES (?, ?, ? , ?, ?, ?)");
	        
	        // "id, name, ability, benevolence, integrity, reputation, timestamp from Entity table");
        	        
			for (int i = 1; i <= n; i++){
				Entity entity = new Entity();
				entity.setEntity_id(i);
				entity.setEntity_name("Entity " + i);
				entity.setAbility(StdRandom.uniform(0.0, 1.0));
				entity.setBenevolence(StdRandom.uniform(0.0, 1.0));
				entity.setIntegrity(StdRandom.uniform(0.0, 1.0));
				entity.setReputation(0.01);
				entity.setEntity_timestamp(new Timestamp(System.currentTimeMillis()));
												
		        preparedStatement.setString(1, entity.getEntity_name());
		        preparedStatement.setDouble(2, entity.getAbility());
		        preparedStatement.setDouble(3, entity.getIntegrity());
		        preparedStatement.setDouble(4, entity.getBenevolence());
		        preparedStatement.setDouble(5, entity.getReputation());
		        preparedStatement.setTimestamp(6, entity.getEntity_timestamp());
		        preparedStatement.executeUpdate();
			}	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    db.close();	
	}	
}