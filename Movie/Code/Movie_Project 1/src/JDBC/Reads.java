package JDBC;
import java.sql.*;
public class Reads {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "#RajuRoy100");
				//here javadb is database name, root is username and password  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			System.out.println("ID" + "  \t" + "Name" + "  \t" + "Age");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  \t" + rs.getString(2) + "  \t" + rs.getInt(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
}
