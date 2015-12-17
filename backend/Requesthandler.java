package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Requesthandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  String jdbcUrl = "jdbc:mysql://as2.c4q4cllm5xqc.us-east-1.rds.amazonaws.com:3306/ebdb?user=zihaolin&password=Zzjdzr910326!";
		  Connection conn = null;
		  
		  
		  try {
			
			    Class.forName("com.mysql.jdbc.Driver");
	
			  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
			  }
		  
		  
		   Statement readStatement = null;
		   ResultSet resultSet = null;	 
		  ArrayList<TweetDetail> res = new ArrayList<>();  
		  	
		   int i=0;
			try {
			    conn = DriverManager.getConnection(jdbcUrl);
			    
			    readStatement = conn.createStatement();
			    
			    
			    resultSet = readStatement.executeQuery("SELECT * FROM TweetStream;");
			    
			    while (resultSet.next()) {
			    		//System.out.println(resultSet.getString("Name"));
			    	TweetDetail td = new TweetDetail();			
						td.setName(resultSet.getString("Name"));
						td.setLatitude(resultSet.getString("Latitude"));
						td.setLongtitude(resultSet.getString("Longtitude"));
						td.setT(resultSet.getString("Tweet"));
						res.add(td);
			    }
			    


			  } catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			  }
			String json = new Gson().toJson(res);
			

			//resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");

			req.setAttribute("list", json.toString());
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		

}
}
