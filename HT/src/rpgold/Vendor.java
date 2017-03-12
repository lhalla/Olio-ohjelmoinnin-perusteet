package rpgold;

public class Vendor extends Creature
{
	private static final double UPGRADECOSTMULTIPLIER = 1.15;
	private final String[] VENDORNAMES = {"Aaron", "Ben", "John", "Kyle", "Obi"};

	private int attackUpgradeCost = 20;
	private int defenseUpgradeCost = 20;
	
	public Vendor()
	{
		super("", 50, 0, 0, 0, 0, 100);
		
		this.name = VENDORNAMES[(int)(VENDORNAMES.length * Math.random())];
		this.type = "Vendor";
	}
	
	public void listPrices()
	{
		System.out.println(String.format("%1s's prices:", name));
		System.out.println(String.format("%10s %4d gold", "+1 attack", attackUpgradeCost));
		System.out.println(String.format("%10s %4d gold", "+1 defense", defenseUpgradeCost));
	}
	
	public void upgradeAttack()
	{
		if (EventManager.getCreature(0).canAfford(attackUpgradeCost))
		{
			EventManager.getCreature(0).upgradeAttack(attackUpgradeCost);
			System.out.println(String.format("You pay %1s gold and increase your attack to %1s!", attackUpgradeCost, EventManager.getCreature(0).getAttack()));
			attackUpgradeCost *= UPGRADECOSTMULTIPLIER;
		}
		else
		{
			System.out.println("You can't afford that.");
		}
	}
	
	public void upgradeDefense()
	{
		if (EventManager.getCreature(0).canAfford(defenseUpgradeCost))
		{
			EventManager.getCreature(0).upgradeDefense(defenseUpgradeCost);
			System.out.println(String.format("You pay %1s gold and increase your defense to %1s!", defenseUpgradeCost, EventManager.getCreature(0).getDefense()));
			defenseUpgradeCost *= UPGRADECOSTMULTIPLIER;
		}
		else
		{
			System.out.println("You can't afford that.");
		}
	}
}
