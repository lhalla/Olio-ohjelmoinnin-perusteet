package demo2;

public class FootballTeamTest
{
	public static void main(String[] args)
	{
		FootballTeam kickers = new FootballTeam("Fruit Bowl");
		kickers.getInfo();
		
		kickers.hirePlayer("Steve", "Goalkeeper", "Left", 1000);
		kickers.hirePlayer("Ryan", "Goalkeeper", "Right", 1200);
		kickers.hirePlayer("Quarn", "Goalkeeper", "Right", 1300);
		kickers.hirePlayer("Quarn", "Back", "Nerf", 1300);
		kickers.hirePlayer("Quarn",  "Back", "Right", 1000);
		kickers.hirePlayer("Kyle", "Forward", "Right", 2000);
		kickers.getInfo();
		
		kickers.firePlayer(3);
		kickers.firePlayer(2);
		kickers.getInfo();
		
		kickers.hirePlayer("Tyler", "Back", "Left", 1500);
		kickers.hirePlayer("Bob", "Back", "Right", 320);
		kickers.hirePlayer("Katarn", "Goalkeeper", "Left", 3141);
		kickers.getInfo();
	}
}
