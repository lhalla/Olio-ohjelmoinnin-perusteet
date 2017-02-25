package rpg2;

public abstract class Creature
{
	protected String name;
	
	protected int health;
	
	public Creature(String name, int health)
	{
		this.name = name;
		this.health = health;
	}
	
	public String getName()
	{
		return name;
	}
}