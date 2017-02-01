package rpg;

public class Player extends Creature
{
	private final int MAXHEALTH = 100;
	private final int POTIONHEAL = 40;
	
	private int nPotions;
	
	public Player(String name)
	{
		super(name, 100, 10, 10, 0.95, 0.05, 50);
		
		this.boolPlayer = true;
		this.nPotions = 0;
		
		System.out.println("Welcome to " + EventManager.WORLD + ", " + name + "!");
	}
	
	public void dance()
	{
		System.out.println("You do a little jig!");
	}
	
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
