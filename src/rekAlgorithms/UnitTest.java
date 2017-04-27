package rekAlgorithms;
import java.sql.*;
import dao.*;
import dbProcess.*;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class UnitTest {

	public static void main(String[] args) {
		ExpCalculation.expMain(1, 6);
	}
	// TODO Auto-generated method stub
	/*
	Connection connect = null;		
	MySQLAccess db = new MySQLAccess();
	int src_id = 1;
	int des_id = 5;
	try {
		connect = db.connect();
		Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_UPDATABLE);
		// Get the number of entities in the entity table
		ResultSet rs = st.executeQuery("SELECT * FROM interaction WHERE source_id = " + src_id + " AND destination_id = " + des_id);	                
		// get the number of rows from the result set		
        Timestamp timestm = new Timestamp(System.currentTimeMillis());
        Timestamp int_timestm = null;
        long diff = 0;
		while (rs.next()) {
            int_timestm = rs.getTimestamp(5);
            diff = timestm.getTime() - int_timestm.getTime();            
            int diff_date = (int)(diff/(1000*60*60*24));
            int diff_month = (int)diff_date/30;
            System.out.println(int_timestm);
            System.out.println(timestm);
            System.out.println(diff);
            System.out.println(diff_date);
            System.out.println(diff_month);
        }
		rs.close();
		st.close();	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    db.close();	
	}
	*/
}