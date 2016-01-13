package chatserver;

import java.sql.*;
import java.util.ArrayList;

/*
 * This will connect to PostgreSQL using JDBC connection. It will take input as
 * 
 * a sql statement as a string and return ArrayList. 
 * 
 * Database it will connect is chatserverdb. It has one table msgdetails, which 
 * 
 * have user1, user2, message, and insert_timestamp. 
 * 
 */

public class dbconnection {
    public ArrayList<String> connectDB(String sql) throws SQLException
    {
    			   				
                   System.out.println("-------- PostgreSQL "+ "JDBC Connection Testing ------------");
                   try {
                                  Class.forName("org.postgresql.Driver");
                   } catch (ClassNotFoundException e) {
                                  System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
                                  e.printStackTrace();
                                  return null;
                   }
                   System.out.println("PostgreSQL JDBC Driver Registered!");
                   Connection connection = null;
                   try {
                                  connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chatserverdb", "postgres","postgres");
                   } catch (SQLException e) {
                                  System.out.println("Connection Failed! Check output console");
                                  e.printStackTrace();
                                  return null;
                   }
                   if (connection != null) {
                                  System.out.println("You made it, take control your database now!");
                   } else {
                                  System.out.println("Failed to make connection!");
                   }
       			
                PreparedStatement preStatement = connection.prepareStatement(sql);
                ArrayList<String> result_data = new ArrayList<String>();
                if (sql.contains("select")){
                				ResultSet result = preStatement.executeQuery();
                				while(result.next()){
                                	result_data.add(result.getString(1).trim()+":"+result.getString(3).trim());
                                	System.out.println("\""+result.getString(1).trim()+"\":\""+result.getString(3).trim()+"\"");                                	
                                	
                                }
                                
                } else {
                				preStatement.executeUpdate();
                				System.out.println("row updated");
                				connection.close();
                }
                connection.close();
            	return result_data;
}
}
