package demo1;

public class Player
{
	// String objects for the name and position of the player
	protected String name, position;

	// The play number of the player
	protected int number;

	// Player constructor
	public Player(String name, int number, String position)
	{
		this.name = name;
		this.number = number;
		this.position = position;
	}

	/**
	 * Returns the name of the player.
	 * @return the name of the player
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the play number of the player.
	 * @return the number of the player
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * Returns the position of the player.
	 * @return the position of the player
	 */
	public String getPosition()
	{
		return position;
	}
	
	public String toString()
	{
		return String.format("%2d: %s, %s", number, name, position);
	}
}