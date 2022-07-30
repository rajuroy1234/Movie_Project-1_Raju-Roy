import java.sql.SQLException;
import java.util.*;

//import users.Create;
import users.Create_User;
import users.Read_User;

import java.io.Console;
import java.io.IOException;

import JDBC.*;

public class MainClass extends Create_User {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args)  
	{
		String port="3306", user="root", name="Roy", pass1="#RajuRoy100";
		int ch=0;
		char[] pass = null;
		String db_URL = "jdbc:mysql://localhost:", db="movie", url="";

		String q_user = "CREATE TABLE users ("
	            + "User_id INT NOT NULL AUTO_INCREMENT, "
	            + "User_name VARCHAR(64) NOT NULL, Email_id VARCHAR(64) NOT NULL UNIQUE, PRIMARY KEY (User_id))", 
	            
	            q_movie = "Create table movie ("
	            		+ "Movie_id INT Auto_increment NOT NULL, "
	            		+ "Movie_name varchar(100) Not Null, Summary varchar(500), "
	            		+ "Genre varchar(200), Cast varchar(200), Average_rating float,"
	            		+ "User_id int DEFAULT 1, FOREIGN KEY (User_id) REFERENCES users(User_id), PRIMARY KEY (Movie_id))",

        		q_review = "CREATE TABLE review ("
	    	            + "Review_id INT NOT NULL AUTO_INCREMENT, Movie_id INT NOT NULL DEFAULT 1,"
	    	            + "Reviews VARCHAR(500),"
	    	            + "User_id int DEFAULT 1, FOREIGN KEY (User_id) REFERENCES users(User_id), FOREIGN KEY (Movie_id) REFERENCES movie(Movie_id), PRIMARY KEY (Review_id))",        		
	            		
	            q_rating = "CREATE TABLE rating ("
	    	            + "Rating_id INT NOT NULL AUTO_INCREMENT, Movie_id INT NOT NULL  DEFAULT 1,"
	    	            + "Ratings INT, User_id INT DEFAULT 1, Email_id VARCHAR(64) NOT NULL UNIQUE DEFAULT '', "
	    	            + "FOREIGN KEY (User_id) REFERENCES users(User_id),"
	    	            + "FOREIGN KEY (Email_id) REFERENCES users(Email_id),"
	    	            + "FOREIGN KEY (Movie_id) REFERENCES movie(Movie_id), PRIMARY KEY (Rating_id))";		
		
		try
		{
			System.out.println("\t\t\t\t\t\tWelcome to Rate and Review Movies \nLet's introduce ourselves! "
					+ "\nI am Liza and what do I call you..?");
			name=sc.nextLine();
			
			System.out.println("Nice to meet you " + name + "!");			
			System.out.println("Please press Enter to continue...");
			sc.nextLine();		//Accepts Enter Key		
		
			System.out.print("Registering your database...\n");			
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
			System.out.println("Database connection established successfully! \nPress Enter...");
			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		//------------------------------------------------------------
		System.out.println("Are you an existing user? Press y or n");
		String ch1=sc.next();
		String em="";
		if(ch1.equals("y") || ch1.equals("Y"))
		{	
			System.out.println("Enter email id: ");
			em = sc.next();
			System.out.println("\nVerifying...");
			Read_User.check(url, user, pass1, em, name);
		}
		else if(ch1.equals("n") || ch1.equals("N"))
		{
			System.out.println("Enter email id: ");
			em=sc.next();
			System.out.println("Creating...\n");
			Create_User.users1(url,user,pass1,name,em);
			em = Create_User.str;		// if email is modified, str stores the modified email
			System.out.println("Your Login Credentials:\nUsername: "+ name 
					+"\nEmail Id: " + em);
		}
		else
		{
			System.out.println("Wrong input..Terminating application!");
			System.exit(0);
		}
		System.out.println("Please press Enter to continue...");
		sc.nextLine();		//Accepts Enter Key	
		sc.nextLine();		//Accepts Enter Key	
		
		char cont='y';
		do{
			System.out.println("Please select one of the following:");
			System.out.println("\n1. View");
			System.out.println("2. Edit");
			ch=sc.nextInt();
			ViewOrEdit obj = new ViewOrEdit();
			try {
				obj.choice(url, user, pass1, em, name, ch);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("\nDo you want to rechoice? \nIf yes, Press 'y'");
			sc.nextLine();
			cont = sc.next().charAt(0);
			
			if(!(cont == 'y' || cont=='Y'))
			{
				System.out.println("Bye! We Thank You for using our service.");
				System.gc();
			}
		}while(cont == 'y' || cont=='Y');
	}

}
