package demo1;

public class Boat
{
	// String object for storing the name of the boat
	protected String name;

	// The length, draft and speed of the boat
	protected double length, draft, speed;

	// Boat constructor
	public Boat(String name, double length, double draft, double speed)
	{
		this.name = name;
		this.length = length;
		this.draft = draft;
		this.speed = speed;
	}

	/**
	 * Returns the time taken, in hours, to travel a given distance, in nautical miles.
	 * @param distance double
	 * @return double, the number of hours required to travel the distance
	 */
	public double travelTimeNM(double nauticalMiles)
	{
		return nauticalMiles / speed;
	}

	/**
	 * Returns the time taken, in hours, to travel a given distance, in kilometres.
	 * @param distance double
	 * @return double, the number of hours required to travel the distance
	 */
	public double travelTimeKM(double kilometres)
	{
		return kilometres / (1.852 * speed); 
	}

	/**
	 * Returns the name of the boat
	 * @return String, the name of the boat
	 */
	public String getName()
	{
		return name;
	}
}