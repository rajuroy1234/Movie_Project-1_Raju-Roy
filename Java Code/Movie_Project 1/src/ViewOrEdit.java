import java.sql.SQLException;
import java.util.*;
public class ViewOrEdit {
	static Scanner sc = new Scanner(System.in);
	public void choice(String url, String uname, String pass, String email, String name, int ch) throws SQLException
	{
		int ch1=0;

			switch(ch)
			{
				case 1:
					System.out.println("You can view the following:\nEnter your choice:\n");
					System.out.println("1. All movies from a specific genre");
					System.out.println("2. Top 10 movies overall"); 
					System.out.println("3. Top 10 movies from a specific genre");
					System.out.println("4. Search Movies");
					System.out.println("5. All Movies");
					ch1=sc.nextInt();
					View.choice(url, uname, pass, ch1);
					break;
					
				case 2:
					System.out.println("You can edit the following:\nEnter your choice:\n");
					System.out.println("1. Add movies");
					System.out.println("2. Edit movies");
					System.out.println("3. Add reviews");
					System.out.println("4. Rate movies");
					ch1=sc.nextInt();
					Edit.choice(url, uname, pass, email, name, ch1);
					break;
					
				default:
					System.out.println("Wrong Choice. Session Ended.");
					System.exit(0);
			}
	}
}
