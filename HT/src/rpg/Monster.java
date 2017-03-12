package rpg;

/**
 * An abstract class for monsters used for targeting.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public abstract class Monster extends Creature implements Hostile
{
	private static final long serialVersionUID = 1L;
	
	protected final int ATTACK, DEFENSE, GOLDREWARD;
	
	public Monster(String name, int health, int attack, int defense, int reward)
	{
		super(name, health);
		this.ATTACK = attack;
		this.DEFENSE = defense;
		this.GOLDREWARD = reward;
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
	public int getReward()
	{
		return GOLDREWARD;
	}

	/**
	 * Attacks against the player. Has a chance to miss or critically hit as determined by the constants
	 * set in the Fightable interface.
	 * @param target the target of the attack
	 */
	@Override
	public void attack(Player target)
	{
		// Determine if the attack hits the target
		if (Math.random() < ACCURACY)
		{
			// If the hit is critical, deal the damage unmitigated
			if (Math.random() < CRITICAL)
			{
				System.out.println(String.format("The %s critically hits you for %s damage.", name, ATTACK));
				target.takeDamage(ATTACK);
			}
			// Otherwise subtract the player's defense value from the monster's attack value
			else
			{
				System.out.println(String.format("The %s hits you for %s damage.", name, Math.max(0, ATTACK - target.getDefense())));
				target.takeDamage(Math.max(0, ATTACK - target.getDefense()));
			}
		}
		else
			System.out.println(String.format("The %s swings at you but misses!", name));
	}
	
	/**
	 * Reduces the creature's health by the amount of damage taken.
	 * @param dmg amount of damage taken
	 */
	@Override
	public void takeDamage(int dmg)
	{
		health = Math.min(health - dmg, MAXHEALTH);
		alive = health > 0;
	}
}
