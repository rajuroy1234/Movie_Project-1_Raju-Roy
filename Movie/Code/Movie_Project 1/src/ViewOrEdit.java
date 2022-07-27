
public class ViewOrEdit {
	public void choice(int ch)
	{
		int ch1=0;
		switch(ch)
		{
			case 1:
				System.out.println("You can view the following:\nEnter your choice:\n\n");
				System.out.println("1. All movies from a specific genre");
				System.out.println("2. Top 10 movies overall");
				System.out.println("3. Top 10 movies from a specific genre");
				System.out.println("4. Search Movies");
				//System.out.println("5. View details");
				
				View obj1=new View();
				View.choice(ch1);
				break;
			case 2:
				System.out.println("You can edit the following:\nEnter your choice:\n\n");
				System.out.println("1. Add movies");
				System.out.println("2. Edit movies");
				System.out.println("3. Add reviews");
				System.out.println("4. Rate movies");
				
				Edit obj2=new Edit();
				Edit.choice(ch1);
				break;
		}
	}
}
