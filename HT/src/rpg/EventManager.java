package rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EventManager
{
	public static final String WORLD = "Craptopia";

	private static ArrayList<Creature> creatures;
	private HashMap<String, Runnable> commands;
	private Scanner reader;

	public EventManager()
	{
		this.reader = new Scanner(System.in);
		creatures = new ArrayList<>();

		introduction();
		createPlayer();
		initCommands();
		getCommand();
	}

	public void introduction()
	{
		System.out.println("Copyright (c) 2017 Lauri & Mikael. All rights reserved.\n");
	}

	public void createPlayer()
	{
		System.out.print("Please enter your name: ");
		creatures.add(new Player(reader.nextLine()));
	}

	public void initCommands()
	{
		commands = new HashMap<>();
		commands.put("dance", () -> creatures.get(0).dance());
		commands.put("drink", () -> creatures.get(0).drink());
		commands.put("quit", () -> quit());
		commands.put("swing", () -> creatures.get(0).swing());
	}

	public void getCommand()
	{
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();

		if (commands.containsKey(input))
			commands.get(input).run();

		getCommand();
	}
	
	public static ArrayList<Creature> getCreatures()
	{
		return creatures;
	}

	public void quit()
	{
		System.out.println(String.format("We hope to welcome you again soon, %1s!", creatures.get(0).getName()));
		System.exit(0);
	}

	public void missSwing(Creature creature)
	{
		System.out.println(creature.isPlayer() ? "You swing and you miss!" : creature.getName() + " tries to attack but misses!");
	}
}
