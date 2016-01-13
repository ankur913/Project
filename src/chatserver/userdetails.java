package chatserver;


import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/user/add")
public class userdetails {
		String chatid;
		String formattedDate = 	new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());

/*	It will take message and chatid from index2.html and insert data into dB.
 * 
*	chatId is unique for every conversion. It can be break into user1 and user2.
*   
*	It passes insert sql to connectBD function which will insert data in database.
*   
*	This function does not return any value
	*/
	
		@Path("/addindb")
		@POST
		public Response messagedetails(@FormParam("textmsg") String textmsg,
									   @FormParam("chatid") String chatid
				  					    ) throws SQLException {
			String user[] = chatid.split(",");
			dbconnection db1 = new dbconnection();
			String insert_sql = "insert into msgdetails values ( '"+user[0]+"','"+user[1]+"','"+textmsg+"','"+formattedDate+"')";
			System.out.println(insert_sql);
			db1.connectDB(insert_sql);
			return null;
			}


/*	It will take chatid from index2.html and fetch data from dB using select statement
 * 
 *	It passes select sql to connectDB function wich will arrylist having name of user and its message.
 * 
 *	This function will return arraylist to getMessages function in index2.html as a form of string
 * 
 *	which will display it on GUI.
 */
	
		@Path("/getMessage")
		@POST
	 			public String printdetails(@FormParam("chatid") String chatid
  								   			) throws SQLException {

				dbconnection db1 = new dbconnection();
				System.out.println(chatid);
				String user[] = chatid.split(",");
				String select_sql = "select * from msgdetails where (upper(user2) = '"+user[1].toUpperCase()+"' and upper(user1) = '"+user[0].toUpperCase()+"') or (upper(user2) = '"+user[0].toUpperCase()+"' and upper(user1) = '"+user[1].toUpperCase()+"') order by insert_time";
				System.out.println(select_sql);
				ArrayList<String> resultset = db1.connectDB(select_sql);
				return resultset.toString();
				}
		}
