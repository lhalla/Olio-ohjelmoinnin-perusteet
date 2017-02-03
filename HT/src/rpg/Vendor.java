package rpg;

public class Vendor extends Creature
{
	private final String[] vendorNames = {"Aaron", "Ben", "John", "Kyle", "Obi"};

	private int attackUpgradeCost = 20;
	private int defenseUpgradeCost = 20;
	
	private static int attackUpgradeCount = 0;
	private static int defenseUpgradeCount = 0;
	
	public Vendor()
	{
		super("", 50, 0, 0, 0, 0, 100);
		
		this.name = vendorNames[(int)(vendorNames.length * Math.random())];
		this.type = "Vendor";
	}
	
	public void initVendorCommands()
	{
		commands.put("upgrade attack",  () -> upgradeAttack(attackUpgradeCost));
		commands.put("upgrade defense", () -> upgradeDefense(defenseUpgradeCost));
	}
	
	public void upgradeAttack(int cost)
	{
		if (EventManager.getCreature(0).canAfford(cost))
		{
			EventManager.getCreature(0).upgradeAttack(cost);
			attackUpgradeCount += 1;
		}
	}
	
	public void upgradeDefense(int cost)
	{
		if (EventManager.getCreature(0).canAfford(cost))
		{
			EventManager.getCreature(0).upgradeDefense(cost);
			defenseUpgradeCount += 1;
		}
	}
}
