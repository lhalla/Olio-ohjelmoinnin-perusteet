package rpg;

/**
 * A class to set the stats for a bear. Used for random enemies in battles.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Bear extends Monster
{
	private static final long serialVersionUID = 1L;
	
	public static final int MINATTACK = 15;
	public static final int RANGEATTACK = 6;
	public static final int MINDEFENSE = 7;
	public static final int RANGEDEFENSE = 5;
	public static final int MAXHEALTH = 70;
	
	public Bear()
	{
		super("Bear", MAXHEALTH, MINATTACK + (int)(Math.random() * RANGEATTACK), MINDEFENSE + (int)(Math.random() * RANGEDEFENSE), 50);
		System.out.println("An angry bear appears!");
	}
}
