package rpg;

import java.util.HashMap;

public class Creature
{
	protected static final int POTIONHEAL = 40;
	protected static final int SANDWICHHEAL = 30;

	protected int attack;
	protected int defense;
	protected int gold;
	protected int health;
	
	protected double accuracy;
	protected double criticalChance;
	
	protected String name;
	protected String type;

	protected HashMap<String, Runnable> commands;

	public Creature(String name, int health, int attack, int defense, double accuracy, double criticalChance, int gold)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.accuracy = accuracy;
		this.criticalChance = criticalChance;
		this.gold = gold;
		
		this.type = "Creature";
	}

	public void drink() {}
	public void eatSandwich() {}

	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}

	public boolean isPlayer()
	{
		return type.equals("Player");
	}
	
	public static void sandwich()
	{
		if (EventManager.getCreatureCount() > 1)
		{
			System.out.println("You tell the " + EventManager.getCreature(1).getType() + " to make you a sandwich.");
			System.out.println("You eat the sandwich recovering " + SANDWICHHEAL + " health.");
		}
		else
		{
			System.out.println("A sandwich manifests itself in front of you. You eat it recovering " + SANDWICHHEAL + " health.");
		}
		
		EventManager.getCreature(0).eatSandwich();
	}
}
