package rekAlgorithms;
import java.sql.*;

// Return time difference in months
public class ComFunc {
	public static int timediff(Timestamp t1, Timestamp t2){
		//t1: time now; t2: previous time		
		long diff = t1.getTime() - t2.getTime();            
        int diff_date = (int)(diff/(1000*60*60*24));
        int diff_month = (int)diff_date/30;
		return diff_month;
	}
}
