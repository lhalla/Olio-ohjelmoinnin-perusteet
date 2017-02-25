package rpg2;

import java.io.Serializable;

public class Player extends Creature implements Playable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int attack, defense;
	private int gold;
	
	public Player(String name)
	{
		super(name, MAXHEALTH);
		this.attack = 10;
		this.defense = 10;
		this.gold = 40;
	}

	@Override
	public int getAttack()
	{
		return attack;
	}

	@Override
	public int getDefense()
	{
		return defense;
	}

	@Override
	public int getHealth()
	{
		return health;
	}

	@Override
	public double getAccuracy()
	{
		return ACCURACY;
	}

	@Override
	public double getCritical()
	{
		return CRITICAL;
	}

	@Override
	public int getGold()
	{
		return gold;
	}

	@Override
	public int attack(Hostile target)
	{
		return Math.max(0, attack - target.getDefense());
	}

	@Override
	public void upgradeAttack(int cost)
	{
		attack++;
		gold -= cost;
	}

	@Override
	public void upgradeDefense(int cost)
	{
		defense++;
		gold -= cost;
	}
	
	/**
	 * Reduces the creature's health by the amount of damage taken.
	 * @param dmg amount of damage taken
	 * @return true if the creature survived
	 */
	public boolean takeDamage(int dmg)
	{
		health -= dmg;
		return health > 0;
	}
	public void sit(){
	    System.out.println("you sit");
	}
}