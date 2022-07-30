package users;
import java.sql.*;

public class Create_User extends Read {
	public static String str = "";
	
	public static String insert(String str, int add, int index) {
	    String beg = str.substring(0,index);
	    String end = str.substring(index);
	    
	    return (beg + add + end);
	}
	
	public static void users1(String url, String uname, String pass, String name, String email) {
		try {
			String q = "select email_id from users where email_id ='" + email +"'";
			String em=email;
			ResultSet rs = res(url, uname, pass, q);
			//System.out.println("Trace");
			
			if(rs.next() && rs.getString(1).equals(em))
			{
				System.out.println("Email already exists. Making an alternate email id.");	
				if(em.indexOf("@")!=-1)
					em=insert(em, (int)(Math.random()* 501), em.indexOf("@"));
				else
					em = em + (int)(Math.random()* 501);
			}
			str = em.toLowerCase();
			Connection con = DriverManager.getConnection(url, uname, pass);
			Statement stmt = con.createStatement();			
			int s = stmt.executeUpdate("insert into users(user_name,email_id) values('" + name + 
					"','" + str
					+ "');");
			System.out.println("Success.\n"); 			
			con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}	
}