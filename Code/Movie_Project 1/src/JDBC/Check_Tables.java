package JDBC;

import java.sql.*;

public class Check_Tables {
	public static void create(String url, String uname, String pass, String q1) throws SQLException
	{	
		Connection con1 = null;
		try{						
			con1 = DriverManager.getConnection(url, uname, pass);
			
			con1.createStatement().executeUpdate(q1);
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally{
			con1.close();
		}
		
	}
	
}
