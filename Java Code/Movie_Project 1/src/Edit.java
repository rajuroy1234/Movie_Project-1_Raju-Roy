import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import users.*;

public class Edit extends Read{
	static Scanner sc = new Scanner(System.in);
	public static void edit(String url, String uname, String pass, String q) {
		try {
			Connection con = DriverManager.getConnection(url, uname, pass);
			Statement stmt = con.createStatement();
			int s = stmt.executeUpdate(q);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public static void choice(String url, String uname, String pass, String email, String name, int ch) throws SQLException
	{
		Connection con = null;
		String movie="Man makes movers", genre="Comedy", cast="Roy", Summary="Push", review="Great!", mid, ratid,
				q1="", q2="", q3="", q4="", q5="",
				qid="Select User_Id from users where Email_id = '"+ email +"'";

		int rating = 4, uid=1, mid1=1;
		float rat=0;
		ResultSet rs = res(url, uname, pass, qid), rs1,rs2,rs3,rs4;
		rs.next();
		uid = rs.getInt(1);
		
		
		switch(ch)
		{
			case 1:					
				System.out.print("Movie name:");
				movie = sc.nextLine();
				System.out.print("\nGenre: ");
				genre = sc.nextLine();
				System.out.print("\nCasts: ");
				cast = sc.nextLine();
				System.out.print("\nAdd Summary: ");
				Summary = sc.nextLine();
				System.out.print("\nAdd Reviews: ");
				review = sc.nextLine();
				System.out.print("\nHow much do u rate this movie? Out of 10");
				rating = sc.nextInt();
					
				System.out.println("");
				q1 = "INSERT IGNORE INTO movie(Movie_name, Genre, Cast, Summary, User_id) VALUES ('" + movie +"','" 
				+ genre + "','" + cast + "','" + Summary + "','" + uid + "')";
				
				edit(url, uname, pass, q1);
				//----------------------------------------------------------------------------------------------
				mid="Select Movie_id from movie where Movie_name = '"+ 
						movie +"'";
				rs1 = res(url, uname, pass, mid);
				rs1.next();
				mid1 = rs1.getInt(1);
				
				q2 = "INSERT IGNORE INTO review(Reviews, Movie_id, User_id) VALUES ('" + review + "','" 
						+ mid1 + "','" + uid + "')";
				
				q3 = "INSERT IGNORE INTO rating(Ratings, Movie_id, User_id, Email_id) VALUES ('" + rating + "','" 
						+ mid1 + "','" + uid  + "','" + email + "')";
				edit(url, uname, pass, q2);
				edit(url, uname, pass, q3);
				//----------------------------------------------------------------------------------------------
				ratid="Select avg(Ratings) from rating where Movie_id = "
						+ mid1 +"";
				rs2 = res(url, uname, pass, ratid);
				rs2.next();
				rat = rs2.getFloat(1);
				q4 = "UPDATE movie SET Average_rating = " + rat + "where Movie_name = '" + movie + "'";
				edit(url, uname, pass, q4);
				
				System.out.println("Movie updated successfully!");
				break;
				
			case 2:
				System.out.println("Movie name?");
				movie = sc.nextLine();
				String q = "SELECT EXISTS(select Movie_name from movie where Movie_name ='"+ movie +"');";
				ResultSet rs8 = res(url, uname, pass, q);
				rs8.next();
				if(rs8.getInt(1)==0)
				{
					System.out.print(movie + " is not available in our database.\nList of available movies: ");
					String quer="Select Movie_name from movie";
					rs = res(url, uname, pass, quer);
					while(rs.next())
					{
						System.out.println("\t" + rs.getString(1));
					}
					System.out.println("Re Enter:");
					choice(url, uname, pass, email , name, 2);
				}
				
				System.out.print("\nGenre: ");
				genre = sc.nextLine();
				System.out.print("\nCasts: ");
				cast = sc.nextLine();
				System.out.print("\nAdd Summary: ");
				Summary = sc.nextLine();					
				System.out.println("");

				//----------------------------------------------------------------------------------------------
				mid="Select Movie_id from movie where Movie_name = '"+ 
						movie +"'";
				ResultSet rs9 = res(url, uname, pass, mid);
				rs9.next();
				mid1 = rs9.getInt(1);
				
				q5 = "UPDATE movie SET Genre = '" + genre + "', Cast = '" + cast + "', Summary = '" + Summary 
						+ "' where Movie_id = " + mid1 + "";
				edit(url, uname, pass, q5);

				System.out.println("Movie updated successfully!");				
				break;
			
			case 3:
				System.out.println("Executing..\nDone.\nPress Enter..");
				sc.nextLine();
				System.out.println("Movie name?");
				movie = sc.nextLine();
				System.out.print("\nAdd Reviews: ");
				review = sc.nextLine();		
				
				mid="Select Movie_Id from movie where Movie_name = '"+ 
						movie +"'";
				rs4 = res(url, uname, pass, mid);
				rs4.next();
				mid1 = rs4.getInt(1);
				
				q2 = "INSERT IGNORE INTO review(Reviews, Movie_id, User_id) VALUES ('" + review + "','" 
						+ mid1 + "','" + uid + "')";
				edit(url, uname, pass, q2);
				System.out.println("Movie Review Added!");
				break;
			
			case 4:
				System.out.println("Executing..\nDone.\nPress Enter..");
				sc.nextLine();
				System.out.println("Movie name?");
				movie = sc.nextLine();
				System.out.print("\nAdd Ratings: ");
				rating = sc.nextInt();		
				
				mid="Select Movie_Id from movie where Movie_name = '"+ 
						movie +"'";
				rs4 = res(url, uname, pass, mid);
				rs4.next();
				mid1 = rs4.getInt(1);
				
				q2 = "INSERT IGNORE INTO rating(Ratings, Movie_id, User_id, Email_id) VALUES ('" + rating + "','" 
						+ mid1 + "','" + uid + "','" + email + "')";
				edit(url, uname, pass, q2);	
				
				//Edit average rating table
				mid="Select Movie_id from movie where Movie_name = '"+ 
						movie +"'";
				rs1 = res(url, uname, pass, mid);
				rs1.next();
				mid1 = rs1.getInt(1);
				ratid="Select avg(Ratings) from rating where Movie_id = "
						+ mid1 +"";
				rs2 = res(url, uname, pass, ratid);
				rs2.next();
				rat = rs2.getFloat(1);
				q4 = "UPDATE movie SET Average_rating = " + rat + "where Movie_name = '" + movie + "'";
				edit(url, uname, pass, q4);
				
				System.out.println("Movie Ratings Added!");
				break;
				
			default:
				System.out.println("Wrong Choice!");

		}
	}
}
