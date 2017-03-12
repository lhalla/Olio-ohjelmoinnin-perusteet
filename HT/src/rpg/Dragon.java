package rpg;

/**
 * A class to set the stats for a dragon. Used for the final boss of the game.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Dragon extends Monster
{
	private static final long serialVersionUID = 1L;
	
	public static final int MINATTACK = 40;
	public static final int RANGEATTACK = 21;
	public static final int MINDEFENSE = 30;
	public static final int RANGEDEFENSE = 11;
	public static final int MAXHEALTH = 250;

	public Dragon()
	{
		super("Algebra the Terrible", MAXHEALTH, MINATTACK + (int)(Math.random() * RANGEATTACK), MINDEFENSE + (int)(Math.random() * RANGEDEFENSE), 0);
		System.out.println("Algebra the Terrible answers your challenge and soars down from the skies!");
	}
}
