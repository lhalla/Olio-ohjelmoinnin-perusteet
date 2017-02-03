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

		System.out.println(String.format("Welcome to %1s, %2s!", EventManager.WORLD, name));
	}

	public boolean canAfford(double price)
	{
		return gold >= price;
	}
	
	public void dance()
	{
		System.out.println("You do a little jig!");
	}
	
	@Override
	public void drink()
	{
		if (nPotions < 1)
		{
			System.out.println("You are out of potions!");
		}
		else if (health == MAXHEALTH)
		{
			System.out.println("You are already at full health!");
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
	
	public void sandwich()
	{
		if (EventManager.getCreatureCount() > 1)
		{
			System.out.println("You tell the " + EventManager.getCreature(1).getType() + " to make you a sandwich.");
			System.out.println("You eat the sandwich recovering " + SANDWICHHEAL + " health.");
		}
		else
		{
			System.out.println("A sandwich manifests itself in front of you. You eat it recovering " + SANDWICHHEAL + " health.");
		}
		
		EventManager.getCreature(0).eatSandwich();
	}
	
	public void upgradeAttack(int cost)
	{
		attack += 1;
		gold -= cost;
	}
	
	public void upgradeDefense(int cost)
	{
		defense += 1;
		gold -= cost;
	}
}