package users;
import java.sql.*;

public class Create {
	
	public static void users1(String url, String uname, String pass, String name, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pass);
			Statement stmt = con.createStatement();

			stmt.executeUpdate("insert into users(user_name,email_id) values('" + name + 
					"','" + email.toLowerCase()
					+ "');");
			System.out.println("Inserted successfully!"); 
			con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}	
}