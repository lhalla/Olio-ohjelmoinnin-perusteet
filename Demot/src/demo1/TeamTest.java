package demo1;

public class TeamTest
{
	public static void main(String[] args)
	{
		Team bonkers = new Team();
		bonkers.getInfo();
		
		bonkers.hirePlayer("Steve", "Forward");
		bonkers.hirePlayer("Jonathan", "Goalkeeper");
		bonkers.hirePlayer("Kyle", "Back");
		bonkers.hirePlayer("Katarn", "Forward");
		bonkers.hirePlayer("Bob", "Builder");
		bonkers.getInfo();
		
		bonkers.firePlayer(2);
		bonkers.firePlayer(5);
		bonkers.getInfo();
		
		bonkers.hirePlayer("Aaron", "Goalkeeper");
		bonkers.getInfo();
	}
}
