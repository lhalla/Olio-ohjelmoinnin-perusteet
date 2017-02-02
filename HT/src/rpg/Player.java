package rpg;

public class Player extends Creature
{
	private final int MAXHEALTH = 100;

	private int nPotions;

	public Player(String name)
	{
		super(name, 100, 10, 10, 0.95, 0.05, 50);

		this.type = "Player";
		this.nPotions = 0;

		System.out.println("Welcome to " + EventManager.WORLD + ", " + name + "!");
	}
	
	@Override
	public int swing()
	{
		if (EventManager.getCreatureCount() > 1)
		{
			Creature target = EventManager.getCreature(1);

			if (this.accuracy <= Math.random())
			{
				if (this.criticalChance <= Math.random())
					return target.takeDamage(this.attack);
				else
					return target.takeDamage(this.attack - target.defense);
			}
			else
				return -1;
		}
		else
		{
			System.out.println("You swing at the air.");
			return -1;
		}
	}

	@Override
	public void dance()
	{
		System.out.println("You do a little jig!");
	}
	
	@Override
	public void drink()
	{
		if (health == MAXHEALTH)
		{
			System.out.println("You are already at full health!");
		}
		else if (nPotions < 1)
		{
			System.out.println("You are out of potions!");
		}
		else
		{
			System.out.println("You drink a potion healing yourself for " + POTIONHEAL + "!");
			health += Math.min(POTIONHEAL, MAXHEALTH - health);
			nPotions -= 1;
		}
	}
	
	@Override
	public void eatSandwich()
	{
		health += Math.min(SANDWICHHEAL, MAXHEALTH - health);
	}
}
