package rpg2;

import java.util.HashMap;
import java.util.Scanner;

public class EventManager
{
	private boolean atVendor, inCombat;
	private Scanner reader;
	private HashMap<String, Runnable> combatCommands, commonCommands, vendorCommands;
	private Player player;
	private Creature creature;
	
	public EventManager()
	{
		reader = new Scanner(System.in);
		
		System.out.print("Do you want to start a new game? YES/no\n: ");
		if (reader.nextLine().toLowerCase().equals("yes"))
		{
			System.out.print("Please enter your name:\n: ");
			player = new Player(reader.nextLine());
			System.out.println(String.format("Welcome to the wondrous world of Objectia, %s!", player.getName()));
		}
	}
}
