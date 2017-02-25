package rpg2;

public class Orc extends Monster
{
	public static final int MINATTACK = 8;
	public static final int RANGEATTACK = 8;
	public static final int MINDEFENSE = 5;
	public static final int RANGEDEFENSE = 5;
	public static final int MAXHEALTH = 50;
	
	public static final int GOLDREWARD = 20;
	
	public Orc(String name, int health, int attack, int defense)
	{
		super("Orc", MAXHEALTH, MINATTACK + (int)(Math.random() * RANGEATTACK), MINDEFENSE + (int)(Math.random() * RANGEDEFENSE));
		System.out.println("An orc appears!");
	}
}