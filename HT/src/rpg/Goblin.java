package rpg;

/**
 * A class to set the stats for a goblin. Used for random enemies in battles.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class Goblin extends Monster
{
	private static final long serialVersionUID = 1L;
	
	public Goblin()
	{
		super("Goblin", MAXHEALTH, MINATTACK + (int)(Math.random() * RANGEATTACK), MINDEFENSE + (int)(Math.random() * RANGEDEFENSE), 15);
		System.out.println("A goblin appears!");
	}
}
