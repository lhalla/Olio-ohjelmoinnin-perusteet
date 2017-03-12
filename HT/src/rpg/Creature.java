package rpg;

import java.io.Serializable;

/**
 * A serialisable abstract class for creatures used in the game.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public abstract class Creature implements Serializable
{
	protected static final long serialVersionUID = 1L;

	protected String name;
	
	protected int health;
	
	// Every creature is by default alive
	protected boolean alive = true;
	
	/**
	 * An empty constructor called when a save is loaded.
	 */
	public Creature()
	{
		
	}
	
	public Creature(String name, int health)
	{
		this.name = name;
		this.health = health;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
}
