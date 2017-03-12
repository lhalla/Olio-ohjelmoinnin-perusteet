package rpg;

/**
 * A class to set the stats for an orc. Used for random enemies in battles.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Orc extends Monster
{
	private static final long serialVersionUID = 1L;
	
	public static final int MINATTACK = 12;
	public static final int RANGEATTACK = 8;
	public static final int MINDEFENSE = 5;
	public static final int RANGEDEFENSE = 5;
	public static final int MAXHEALTH = 50;
	
	public Orc()
	{
		super("Orc", MAXHEALTH, MINATTACK + (int)(Math.random() * RANGEATTACK), MINDEFENSE + (int)(Math.random() * RANGEDEFENSE), 20);
		System.out.println("An orc appears!");
	}
}
