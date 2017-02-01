package rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EventManager
{
	public static final String WORLD = "Craptopia";
	
	private ArrayList<Creature> creatures;
	private HashMap<String, Runnable> commands;
	private Scanner reader;
	
	public EventManager()
	{
		introduction();
		
		this.reader = new Scanner(System.in);
		
		creatures = new ArrayList<>();
		
		createPlayer();
	}
	
	public void introduction()
	{
		System.out.println("Copyright (c) 2017 Lauri & Mikael. All rights reserved.");
	}
	
	public void createPlayer()
	{
		System.out.print("Please enter your name: ");
		creatures.add(new Player(reader.nextLine()));
	}
	
	public void missSwing(Creature creature)
	{
		System.out.println(creature.isPlayer() ? "You swing and you miss!" : creature.getName() + " tries to attack but misses!");
	}
}
