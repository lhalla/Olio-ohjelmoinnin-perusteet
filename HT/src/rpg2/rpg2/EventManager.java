package rpg2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class EventManager
{
	private boolean atVendor, inCombat;
	private Scanner reader;
	private HashMap<String, Runnable> combatCommands, generalCommands, vendorCommands;
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
			initCommands();
			
		}
		String input;
		while(!atVendor && !inCombat){
			getCommand();
		}
	}
	
	//Enter Commands here
	
	private void initCommands()
	{
		initGeneralCommands();
		initVendorCommands();
	}
	
	private void initGeneralCommands()
	{
		generalCommands = new HashMap<>();
		generalCommands.put("sit",                   () -> player.sit());
	}
	
	private void initVendorCommands()
	{
		vendorCommands = new HashMap<>();
	}
	private void initCombatCommands()
	{
		combatCommands = new HashMap<>();
		Monster m=(Monster) creature;
		combatCommands.put("attack",                   () -> player.attack(m));
	}
	
	
	private void getCommand()
	{
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();

		if (atVendor)
		{
			if (vendorCommands.containsKey(input))
				vendorCommands.get(input).run();
			else System.out.println("you can't do that while at vendor");
		}
		else if(inCombat){
			if (combatCommands.containsKey(input))combatCommands.get(input).run();
			else System.out.println("you can't do that while in combat");		
		}
		else
		{
			if (generalCommands.containsKey(input))
				generalCommands.get(input).run();
			else System.out.println("you can't do that");
		}

		getCommand();
	}
	
	//Commands end here

	public void combat(){
		initCombatCommands();
		inCombat=true;
		System.out.println("you are challenged by " + creature.getName());
		while(inCombat){
			getCommand();
			Monster m = (Monster) creature;
			if(creature==null)inCombat=false;
			else m.attack(player);
		}
		
	}
}
