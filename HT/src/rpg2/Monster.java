package rpg2;

public abstract class Monster extends Creature implements Hostile
{
	protected final int ATTACK, DEFENSE;
	
	public Monster(String name, int health, int attack, int defense)
	{
		super(name, health);
		this.ATTACK = attack;
		this.DEFENSE = defense;
	}

	@Override
	public int getAttack()
	{
		return ATTACK;
	}

	@Override
	public int getDefense()
	{
		return DEFENSE;
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
	public int attack(Playable target)
	{
		return Math.max(0, ATTACK - target.getDefense());
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
}