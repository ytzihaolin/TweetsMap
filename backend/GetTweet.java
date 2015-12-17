package backend;


import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;


public final class GetTweet {

	static Connection conn = null;
  
	protected static void go()
			throws ServletException, IOException {
    	
    	

    	String jdbcUrl = "jdbc:mysql://as2.c4q4cllm5xqc.us-east-1.rds.amazonaws.com:3306/ebdb?user=zihaolin&password=Zzjdzr910326!";
		  
    	 ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
           .setOAuthConsumerKey("gAam4xYo1K9raecwx5ocBpX0M")
           .setOAuthConsumerSecret("X7pFpfdeyqVjerygpZRRYSvCDo1yTBQU9PV3p1aPe8nN4vyVAw")
           .setOAuthAccessToken("2779935535-snVwMmXgOYx58DV6CFJNRj2t6gxzhhjkCxYfrga")
           .setOAuthAccessTokenSecret("MldTeewgjnxanQn3l3rI1P2M4L6W0mNTA7gvANwpk6ZTF");
    	 
		  // Load the JDBC Driver
		  try {
		    System.out.println("Loading driver...");
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
		  
		  try {
			    // Create connection to RDS instance
			    conn = DriverManager.getConnection(jdbcUrl);
		    
			  } catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			  } 
		  
		  try{
		    Statement setupStatement = conn.createStatement();
		    String createTable = "CREATE TABLE TweetStream (Name char(50),Tweet char(255),Longtitude Double,Latitude Double,Time TIMESTAMP);";
		    setupStatement.addBatch(createTable);
		    setupStatement.executeBatch();
		  }catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			  } 
		  

		  
		  
		  
		  
		  TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	        StatusListener listener = new StatusListener() {
	            @Override
	            public void onStatus(Status status) {
	            String lang = status.getLang();
	          	if(status.getGeoLocation()!=null && lang.equals("en")){
						try {
							GetTweets(status);
						} catch (Exception e) {
							e.printStackTrace();
						}
	            	} 
	            }

	            @Override
	            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

	            }

	            @Override
	            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	
	            }

	            @Override
	            public void onScrubGeo(long userId, long upToStatusId) {

	            }

	            @Override
	            public void onStallWarning(StallWarning warning) {

	            }

	            @Override
	            public void onException(Exception ex) {
	                ex.printStackTrace();
	            }
	        };
	        

		
	
		
			// Listener
		    twitterStream.addListener(listener);

		    // Filter
		    twitterStream.sample();

		  //write to db
		  
    }
    
    public static void GetTweets(Status tweet) throws Exception{
    	
   	 Statement setupStatement = conn.createStatement();
	     String add = "INSERT INTO TweetStream (Name,Tweet,Longtitude,Latitude) VALUES('"+tweet.getUser().getScreenName().toString()+"', '"+tweet.getText()+"' ,'"+tweet.getGeoLocation().getLongitude()+"','"+tweet.getGeoLocation().getLatitude()+"');";
	
		 setupStatement.execute(add);
		
	}
   
}