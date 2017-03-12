package rpg;

/**
 * A player class used for the character of the player.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Player extends Creature implements Playable
{
	private static final long serialVersionUID = 1L;

	private int attack, defense, potions;
	private int gold;
	
	private int magicPower = 20;
	private boolean knowsFireball;

	/**
	 * An empty constructor called when a save is loaded.
	 */
	public Player()
	{

	}

	public Player(String name)
	{
		super(name, MAXHEALTH);
		this.attack = 10;
		this.defense = 10;
		this.potions = 0;
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
	public int getGold()
	{
		return gold;
	}
	
	@Override
	public int getPotions()
	{
		return potions;
	}

	/**
	 * Attacks against a specified monster target. Has a chance to miss or critically hit as determined
	 * by the constants set in the Fightable interface.
	 * @param target the target of the attack
	 */
	@Override
	public void attack(Monster target)
	{
		// Determine if the attack hits the target
		if (Math.random() < ACCURACY)
		{
			// If the hit is critical, deal the damage unmitigated
			if (Math.random() < CRITICAL)
			{
				System.out.println(String.format("You critically hit the %s for %s damage.", target.getName(), attack));
				target.takeDamage(attack);
			}
			// Otherwise subtract the target's defense value from the player's attack value
			else
			{
				System.out.println(String.format("You hit the %s for %s damage.", target.getName(), Math.max(0, attack - target.getDefense())));
				target.takeDamage(Math.max(0, attack - target.getDefense()));
			}
		}
		else
			System.out.println("You miss the target.");
	}

	/**
	 * Reduces the creature's health by the amount of damage taken. Updates the player's living status
	 * if needed.
	 * @param dmg amount of damage taken
	 */
	@Override
	public void takeDamage(int dmg)
	{
		health = Math.min(health - dmg, MAXHEALTH);
		alive = health > 0;
	}
	
	/**
	 * Consumes a potion, if available, to heal the player character by an amount set in the Playable
	 * interface.
	 */
	@Override
	public void drinkPotion()
	{
		// Determines if there are potions available and if there is health missing
		if (potions > 0 && health < MAXHEALTH)
		{
			// The player is healed at most by the amount of a potion's healing potency but their health
			// can not exceed the maximum
			int healed = Math.min(POTIONHEAL, MAXHEALTH - health);
			System.out.println(String.format("You drink a potion healing you for %s.", healed));
			
			health += healed;
			potions--;
		}
		else if (potions == 0)
			System.out.println("You are out of potions. Visit a vendor to buy more.");
		else
			System.out.println("You are already at full health.");
	}

	/**
	 * Increases the player's attack stat by a specified amount. Reduces the available gold by an amount
	 * equal to the specified cost of the upgrade.
	 * @param step the amount by which the stat is increased
	 * @param cost the amount of gold the upgrade costs
	 */
	@Override
	public void upgradeAttack(int step, int cost)
	{
		attack += step;
		gold -= cost;
	}

	/**
	 * Increases the player's defense stat by a specified amount. Reduces the available gold by an amount
	 * equal to the specified cost of the upgrade.
	 * @param step the amount by which the stat is increased
	 * @param cost the amount of gold the upgrade costs
	 */
	@Override
	public void upgradeDefense(int step, int cost)
	{
		defense += step;
		gold -= cost;
	}
	
	/**
	 * Allows the player to use the Fireball spell. Reduces the available gold by an amount equal to
	 * the specified cost of the upgrade.
	 * @param cost the amount of gold the upgrade costs
	 */
	@Override
	public void learnFireball(int cost)
	{
		knowsFireball = true;
		gold -= cost;
	}
	
	
	/**
	 * Returns true if the character has learned the Fireball spell.
	 * @return true if the character has learned the Fireball spell
	 */
	@Override
	public boolean knowsFireball()
	{
		return knowsFireball;
	}
	
	/**
	 * Casts the Fireball spell at the specified enemy.
	 * @param target the target of the attack
	 */
	@Override
	public void castFireball(Monster target)
	{
		// Determine if the character has learned the Fireball spell
		if (knowsFireball)
		{
			System.out.println("You start conjuring a massive fireball.");
			
			// Determine whether the spell hits the target
			if (Math.random() < MAGACCURACY)
			{
				// A successful spell hits for a random amount between 20 and 40
				int power = (int) ((Math.random() + 1) * magicPower);
				System.out.println(String.format("You cast the fireball at the %s, dealing %s damage.", target.getName(), power));
				target.takeDamage(power);
			}
			// Otherwise it backfires and hurts the caster
			else
			{
				// A backfired spell hits the caster for a random amount between 10 and 20
				int power = (int) ((Math.random() + 1) * magicPower / 2);
				System.out.println(String.format("The winds of magic are fickle. The fireball explodes in your hands, dealing %s damage to you.", power));
				takeDamage(power);
			}
		}
		else
			System.out.println("You wave your hands about but nothing happens.");
	}
	
	/**
	 * Increases the number of available potions by one. Reduces the amount of available gold by an
	 * amount equal to the cost of the potion.
	 * @param cost the cost of the potion
	 */
	@Override
	public void addPotion(int cost)
	{
		potions++;
		gold -= cost;
	}
	
	/**
	 * Increases the amount of available gold by the specified amount.
	 * @param gold the amount of gold added
	 */
	@Override
	public void earnGold(int gold)
	{
		this.gold += gold;
	}

	/**
	 * Lists the character's following current stats: health, attack, defense, number of potions and gold.
	 */
	@Override
	public void listStats()
	{
		System.out.println(String.format("Health: %s / %s\nAttack: %s\nDefense: %s\nPotions: %s\nGold: %s", health, MAXHEALTH, attack, defense, potions, gold));
	}
}
