package users;
import java.sql.*;
public class Read_User {
	public static void check(String url, String uname, String pass, String email, String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pass);				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT EXISTS(select * from users where email_id ='"+email+"');");			
			rs.next();
			if(rs.getInt(1)==0)
			{
				System.out.print("You are 'not' an existing user. Moving you into our database...");
				Create.users1(url, uname, pass, name, email);
				System.out.println("You are a verified user now.");
			}
			else
				System.out.println("Verified!");
				
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}	
}
