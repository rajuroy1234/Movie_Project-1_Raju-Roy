import java.util.*;
import users.Create;
import users.Read_User;
import java.io.Console;
import java.io.IOException;
import JDBC.*;

public class MainClass {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws IOException, InterruptedException {
		String port="3306",user="root",name="Roy", pass1="#RajuRoy100";	
		String q_user = "CREATE TABLE users ("
	            + "user_id INT NOT NULL AUTO_INCREMENT, "
	            + "user_name VARCHAR(64) NOT NULL, email_id VARCHAR(64) NOT NULL UNIQUE, PRIMARY KEY (user_id))", 
	            
	            q_movie = "Create table movie (movie_id INT Auto_increment NOT NULL, "
	            		+ "movie_name varchar(100) Not Null,Summary varchar(500),"
	            		+ "cast  varchar(200),Genre varchar(200),Average_rating float,"
	            		+ "user_id int, FOREIGN KEY (user_id) REFERENCES users(user_id), PRIMARY KEY (movie_id));",

        		q_review = "CREATE TABLE review ("
	    	            + "review_id INT NOT NULL AUTO_INCREMENT, Movie_Id INT NOT NULL,"
	    	            + "reviews VARCHAR(500),"
	    	            + "user_id int, FOREIGN KEY (user_id) REFERENCES users(user_id), PRIMARY KEY (review_id));",        		
	            		
	            q_rating = "CREATE TABLE rating ("
	    	            + "rating_id INT NOT NULL AUTO_INCREMENT, Movie_Id INT NOT NULL,"
	    	            + "ratings INT, user_id INT, email_id VARCHAR(64) NOT NULL UNIQUE, PRIMARY KEY (rating_id))";
		
		int ch=0;
		char[] pass = null;
		String db_URL="jdbc:mysql://localhost:",db="movie4",url="";
		
		try
		{
			System.out.println("Welcome Rate and Review Movies \nLet's introduce ourselves! \nI am Liza and You are..?");
			name=sc.nextLine();
			System.out.println("Nice to meet you "+ name +"!");			
			System.out.println("Please press Enter to continue...");
			sc.nextLine();		//Accepts Enter Key		
		
			System.out.print("\n\nRegistering your database...\n \n");			
			//-------------------------------------------------------------------------------
			System.out.print("\nEnter Port number(3306 by default): ");
			port=sc.nextLine();
			
			System.out.print("\nEnter your database username(root by default): ");
			user=sc.nextLine();			
			
			System.out.print("\nEnter your database password: ");			
			//Run in the cmd console, masks the password, Eclipse don't have the required console
			
			Console cons = System.console();
			if ((cons = System.console()) != null)
				pass = cons.readPassword("Enter password"); 
			else
				pass=sc.next().toCharArray();
			
			db_URL+=port+"/";	
			url+=db_URL + db;		//convert to full URL
			pass1 = String.valueOf(pass);		//convert to string	
			
			Check_Database.dbexists(db_URL, user, pass1,db);
			
			System.out.println("Checking tables....");
			Check_Tables.create(url, user, pass1,q_user);	
			Check_Tables.create(url, user, pass1,q_movie);
			Check_Tables.create(url, user, pass1,q_review);	
			Check_Tables.create(url, user, pass1,q_rating);	
			System.out.println("Done.");
			System.out.println("Connection established Successfully! Press Enter...\n");

			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		//------------------------------------------------------------
		System.out.println("\nAre you new user? Press y or n");
		String ch1=sc.next();
		if(ch1.equals("y"))
		{	String em;
			System.out.println("Enter email id: ");
			em = sc.next();
			System.out.println("Verifying...\n");
			Read_User.check(url, user, pass1, em, name);
		}
		else if(ch1.equals("n"))
		{
			String em;
			System.out.println("Enter email id: ");
			em=sc.next();
			System.out.println("Creating...\n");
			Create.users1(url,user,pass1,name,em);
		}
		System.out.println("Please press Enter to continue...");
		sc.nextLine();		//Accepts Enter Key	
		sc.nextLine();		//Accepts Enter Key	
		System.out.println("Please select one of the following:");
		System.out.println("\n1. View");
		System.out.println("2. Edit");
		ch=sc.nextInt();
		ViewOrEdit obj=new ViewOrEdit();
		obj.choice(ch);		
	}

}
