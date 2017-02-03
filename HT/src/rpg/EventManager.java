package rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EventManager
{
	private boolean playerAtVendor;
	private boolean playerInCombat;
	
	// A Scanner for reading user input.
	private Scanner reader;
	
	// World name
	public static final String WORLD = "Craptopia";

	// An ArrayList for the current Creatures
	private static ArrayList<Creature> creatures;
	
	// A HashMap for all recognized user commands
	private HashMap<String, Runnable> generalCommands;
	private HashMap<String, Runnable> vendorCommands;
	
	/*
	 * Constructor. Initializes the Scanner and Creature list, spawns the Player character and initializes the command list.
	 */
	public EventManager()
	{
		reader = new Scanner(System.in);
		creatures = new ArrayList<>();

		introduction();
		spawnPlayer();
		initCommands();
		initVendorCommands();
		getCommand();
	}
	
	/* GENERAL METHODS */
	
	/*
	 * Prints an introductory message to the player.
	 */
	private void introduction()
	{
		System.out.println("Copyright (c) 2017 Lauri & Mikael. All rights reserved.\n");
	}

	/*
	 * Initializes the dictionary of known user commands.
	 */
	private void initCommands()
	{
		generalCommands = new HashMap<>();
		generalCommands.put("dance",                   () -> creatures.get(0).dance());
		generalCommands.put("drink",                   () -> creatures.get(0).drink());
		generalCommands.put("quit",                    () -> quit());
		generalCommands.put("shop",                    () -> enterShop());
		generalCommands.put("sudo make me a sandwich", () -> creatures.get(0).sandwich());
	}
	
	public void initVendorCommands()
	{
		vendorCommands = new HashMap<>();
		vendorCommands.put("leave",                   () -> leaveShop());
		vendorCommands.put("list prices",             () -> creatures.get(1).listPrices());
		vendorCommands.put("sudo make me a sandwich", () -> creatures.get(0).sandwich());
		vendorCommands.put("upgrade attack",          () -> creatures.get(1).upgradeAttack());
		vendorCommands.put("upgrade defense",         () -> creatures.get(1).upgradeDefense());
	}
	
	/*
	 * Prompts the user for a command input. If the input is recognized, it is executed.
	 */
	private void getCommand()
	{
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();

		if (generalCommands.containsKey(input))
			generalCommands.get(input).run();

		getCommand();
	}
	
	/**
	 * Quits the game.
	 */
	private void quit()
	{
		System.out.println(String.format("We hope to welcome you again soon, %1s!", creatures.get(0).getName()));
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.exit(0);
	}

	/* CREATURE HANDLING METHODS */
	
	/**
	 * Returns the requested spawned Creature.
	 * @param index, 
	 * @return
	 */
	public static Creature getCreature(int index)
	{
		if (0 <= index && index < creatures.size())
		{
			return creatures.get(index);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Returns the current number of spawned Creatures.
	 * @return
	 */
	public static int getCreatureCount()
	{
		return creatures.size();
	}
	
	private void killVendor()
	{
		creatures.remove(1);
		this.playerAtVendor = false;
	}

	/**
	 * Asks the user for a name for their character and then spawns it.
	 */
	private void spawnPlayer()
	{
		System.out.print("Please enter your name: ");
		creatures.add(new Player(reader.nextLine()));
	}
	
	private void spawnVendor()
	{
		creatures.add(new Vendor());
		this.playerAtVendor = true;
	}
	
	/* COMBAT METHODS */
	
	/* VENDOR METHODS */
	
	private void enterShop()
	{
		if (playerAtVendor || playerInCombat)
		{
			System.out.println("You can't do that right now.");
		}
		else
		{
			spawnVendor();
			System.out.println(String.format("You go to town and find %1s the %2s.", creatures.get(1).getName(), creatures.get(1).getType()));
			getShopCommand();
		}
	}
	
	private void getShopCommand()
	{
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();

		if (vendorCommands.containsKey(input))
			vendorCommands.get(input).run();

		getShopCommand();
	}
	
	private void leaveShop()
	{
		if (!playerAtVendor || playerInCombat)
		{
			System.out.println("You can't do that right now.");
		}
		else
		{
			killVendor();
			System.out.println("You leave the shop and return on your adventure.");
			getCommand();
		}
	}
}
