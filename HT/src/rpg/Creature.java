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
		commands = new HashMap<>();
	}
	
	public boolean canAfford(int price)
	{
		return false;
	}
	
	public void dance() {}
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
	
	public void sandwich() {}
	public void upgradeAttack(int cost) {}
	public void upgradeDefense(int cost) {}
}
