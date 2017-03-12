package rpg;

/**
 * A class for a vendor. Used to buy upgrades from for the player character.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Vendor extends Creature
{
	private static final long serialVersionUID = 1L;
	
	private static int attackUpgradeCost = 20;
	private static int defenseUpgradeCost = 20;
	private static int potionCost = 20;
	private static int excaliburCost = 200;
	private static int fireballCost = 100;
	
	private static boolean soldExcalibur, soldFireball;
	private static int upgradeStep = 2;
	
	private static double costMultiplier = 1.15;

	public Vendor()
	{
		super("Shopkeep Tyrone", 0);
	}
	
	/**
	 * Lists the commands available to the player.
	 */
	public void help()
	{
		System.out.println(String.format("%-20s Upgrade your attack stat by %s.", "buy attack", upgradeStep));
		System.out.println(String.format("%-20s Upgrade your defense stat by %s.", "buy defense", upgradeStep));
		System.out.println(String.format("%-20s Buy Excalibur, the mythic sword of legendary fables.", "buy excalibur"));
		System.out.println(String.format("%-20s Buy a potion.", "buy potion"));
		System.out.println(String.format("%-20s Learn the Fireball spell.", "learn fireball"));
		System.out.println(String.format("%-20s Leave the shop.", "leave"));
		System.out.println(String.format("%-20s List the prices of the vendor's goods.", "list goods"));
		System.out.println(String.format("%-20s List your current stats.", "list stats"));
	}
	
	/**
	 * Lists the prices of the available purchases and upgrades.
	 */
	public void listPrices()
	{
		System.out.println(String.format("%s's prices:", name));
		System.out.println(String.format("%-20s %4s gold", String.format("+%s attack", upgradeStep), attackUpgradeCost));
		System.out.println(String.format("%-20s %4s gold", String.format("+%s defense", upgradeStep), defenseUpgradeCost));
		System.out.println(String.format("%-20s %4s gold", "Excalibur", (!soldExcalibur ? excaliburCost : "-")));
		System.out.println(String.format("%-20s %4s gold", "Fireball", (!soldFireball ? fireballCost : "-")));
		System.out.println(String.format("%-20s %4s gold", "+1 potion", potionCost));
	}
	
	/**
	 * Upgrades the player's attack stat by an amount equal to the set upgrade step.
	 * @param player the player whose attack stat is increased
	 */
	public void upgradeAttack(Player player)
	{
		// If the player can afford the upgrade, call the player's respective upgrade method
		if (player.getGold() >= attackUpgradeCost)
		{
			player.upgradeAttack(upgradeStep, attackUpgradeCost);
			System.out.println(String.format("You pay %s gold and increase your attack to %s!", attackUpgradeCost, player.getAttack()));
			attackUpgradeCost *= costMultiplier;
		}
		else
			System.out.println("You can not afford that.");
	}
	
	/**
	 * Upgrades the player's defense stat by an amount equal to the set upgrade step.
	 * @param player the player whose defense stat is increased
	 */
	public void upgradeDefense(Player player)
	{
		// If the player can afford the upgrade, call the player's respective upgrade method
		if (player.getGold() >= defenseUpgradeCost)
		{
			player.upgradeDefense(upgradeStep, defenseUpgradeCost);
			System.out.println(String.format("You pay %s gold and increase your defense to %s!", defenseUpgradeCost, player.getDefense()));
			defenseUpgradeCost *= costMultiplier;
		}
		else
			System.out.println("You can not afford that.");
	}
	
	/**
	 * Increases the amount of the player character's available potions.
	 * @param player the player who will receive the potion
	 */
	public void sellPotion(Player player)
	{
		// If the player can afford the potion, call the player's respective purchase method
		if (player.getGold() >= potionCost)
		{
			player.addPotion(potionCost);
			System.out.println(String.format("You pay %s gold for a potion. You currently have %s potion%s.", potionCost, player.getPotions(), (player.getPotions() == 1 ? "" : "s")));
		}
		else
			System.out.println("You can not afford that.");
	}
	
	/**
	 * A one-time upgrade to the player's attack stat equal to three times the normal upgrade step.
	 * @param player the player whose attack stat is increased
	 */
	public void sellExcalibur(Player player)
	{
		// If the player can afford the upgrade and the vendor hasn't sold it yet, call the player's
		// respective upgrade method
		if (player.getGold() >= excaliburCost && !soldExcalibur)
		{
			player.upgradeAttack(3*upgradeStep, excaliburCost);
			soldExcalibur = true;
			System.out.println(String.format("You pay %s gold to buy the Excalibur, increasing your attack to %s!", excaliburCost, player.getAttack()));
		}
		else if (soldExcalibur)
			System.out.println(String.format("%s has already sold you the Excalibur.", name));
		else
			System.out.println("You can not afford that.");
	}
	
	/**
	 * A one-time upgrade that allows the player to cast the Fireball spell in combat in addition
	 * to their normal attack.
	 * @param player the player who will learn the spell
	 */
	public void teachFireball(Player player)
	{
		// If the player can afford the upgrade and the vendor hasn't taught it yet, call the player's
		// respective upgrade method
		if (player.getGold() >= fireballCost && !soldFireball)
		{
			player.learnFireball(fireballCost);
			soldFireball = true;
			System.out.println("You feel the winds of magic flow through you.");
		}
		else if (soldFireball)
			System.out.println(String.format("%s has already taught you the Fireball spell.", name));
		else
			System.out.println("You can not afford that.");
	}
}
