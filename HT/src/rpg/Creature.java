package rpg;

import java.util.HashMap;

public class Creature
{
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

	public void dance() {}
	public void drink() {}
}
