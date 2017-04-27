package dbProcess;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
        
  private Connection connect = null;

  final private String host = "localhost:3306";
  final private String user = "root";
  final private String passwd = "nguyentb123";  
  final private String db_name = "/trustevaluation?"; 

  public Connection connect() throws Exception{
	  if(connect != null) return connect;
	
	  try {
	      Class.forName("com.mysql.jdbc.Driver");
	  } catch (ClassNotFoundException e) {
	      throw new Exception("No database");
	  }
	
	  String connectionURL = "jdbc:mysql://" + host + db_name + "verifyServerCertificate=false&useSSL=true";
	  connect = DriverManager.getConnection(connectionURL, user, passwd);
	  return connect;
  }  
  
  public void close(){	  
	  if(connect != null){
	    try {
	        connect.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	  }
  }

}

