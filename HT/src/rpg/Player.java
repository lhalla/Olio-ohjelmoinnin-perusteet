package rpg;

import java.util.ArrayList;

public class Player extends Creature
{
	private final int MAXHEALTH = 100;
	private final int POTIONHEAL = 40;

	private int nPotions;

	public Player(String name)
	{
		super(name, 100, 10, 10, 0.95, 0.05, 50);

		this.type = "Player";
		this.nPotions = 0;

		System.out.println("Welcome to " + EventManager.WORLD + ", " + name + "!");
	}
	
	// ToDo: Sandwich 
	
	@Override
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
			health += POTIONHEAL;
			nPotions -= 1;
		}
	}
}
