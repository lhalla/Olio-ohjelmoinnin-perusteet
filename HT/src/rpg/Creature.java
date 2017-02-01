package rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Creature
{
	protected String name;
	protected boolean boolPlayer;

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

		this.commands = new HashMap<>();
	}

	public int swing()
	{
		ArrayList<Creature> creatures = EventManager.getCreatures();

		if (creatures.size() > 1)
		{
			if (this.accuracy <= Math.random())
			{
				if (this.criticalChance <= Math.random())
					return creatures.get(1).takeDamage(this.attack);
				else
					return creatures.get(1).takeDamage(this.attack - creatures.get(1).defense);
			}
			else
			{
				return -1;
			}
		}
		else
		{
			System.out.println("You swing at the air.");
			return -1;
		}
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
		return boolPlayer;
	}

	public String getName()
	{
		return name;
	}

	public void dance() {}
	public void drink() {}
}
