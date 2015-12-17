package backend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;






public class DeletTweet {
	public static void go()  {
		// TODO Auto-generated method stub
		
		String jdbcUrl = "jdbc:mysql://as2.c4q4cllm5xqc.us-east-1.rds.amazonaws.com:3306/ebdb?user=zihaolin&password=Zzjdzr910326!";		 
		Connection conn = null;
		  
		  
		  try {
			
			    Class.forName("com.mysql.jdbc.Driver");
	
			  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
			  }
		  	Statement deleteStatement = null;

		  	
		  	while(true){
				try {
				    conn = DriverManager.getConnection(jdbcUrl);
				    
				    deleteStatement = conn.createStatement();
	
				    deleteStatement.execute("DELETE FROM TweetStream WHERE Time < (NOW() - INTERVAL 1000 MINUTE);");
				   
				    
	
				  } catch (SQLException ex) {
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				  }
				
				try{
					Thread.sleep(10000);
				}catch(Exception e){}

		  	}

}
}