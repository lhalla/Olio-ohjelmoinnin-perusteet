package rpg;

import java.util.HashMap;

public class Creature
{
	protected static final int POTIONHEAL = 40;
	protected static final int SANDWICHHEAL = 30;
	
	protected String name;
	protected String type;

	protected int health;
	protected int attack;
	protected int defense;
	protected double accuracy;
	protected double criticalChance;

	protected int gold;

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

	public int swing()
	{
		return 0;
	}

	public int takeDamage(int damage)
	{
		this.health -= damage;

		if (this.health <= 0)
			return -2;

		return damage;
	}

	public boolean isPlayer()
	{
		return type.equals("Player");
	}

	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}

	public void dance() {}
	public void drink() {}
	public void eatSandwich() {}
	
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
