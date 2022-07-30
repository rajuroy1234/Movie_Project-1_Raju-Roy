package users;
import java.sql.*;

public class Read {
	public static ResultSet res(String url, String uname, String pass, String q) throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, pass);				
			Statement stmt = con.createStatement();
			//System.out.println("Trace");
			//int s=stmt.executeUpdate(q);
			//System.out.println("Trace");
			//System.out.println(s + " Records found."); 
//			if(s==0)
//				System.exit(0);
			rs = stmt.executeQuery(q);			
			//con.close();
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		return rs;
		
		
	}
}
