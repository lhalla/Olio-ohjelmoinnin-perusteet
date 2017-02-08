package demo2;

import demo1.Boat;

public class FerryBoat extends Boat
{
	protected final int CAPACITY;
	protected final int LOADPERPASSENGER = 1;
	protected final int LOADPERVEHICLE = 10;
	protected int passengerCount = 0;
	protected int vehicleCount = 0;

	public FerryBoat(String name, double length, double draft, double speed, int capacity)
	{
		super(name, length, draft, speed);

		this.CAPACITY = capacity;
	}

	public boolean addPassenger(int count)
	{
		if (count * LOADPERPASSENGER + getCurrentLoad() > CAPACITY)
		{
			System.out.println("Unable to add " + count + " passenger(s). Space available for max. " + ((CAPACITY - getCurrentLoad()) / LOADPERPASSENGER) + " passenger(s).");
			return false;
		}
		else
		{
			System.out.println(count + " more passenger(s) now onboard.");
			passengerCount += count;
			return true;
		}
	}
	
	public boolean removePassenger(int count)
	{
		if (count > passengerCount)
		{
			System.out.println("Unable to remove " + count + " passenger(s). " + passengerCount + " passenger(s) onboard.");
			return false;
		}
		else
		{
			System.out.println(count + " passenger(s) removed.");
			passengerCount -= count;
			return true;
		}
	}

	public boolean addVehicle(int count)
	{
		if (count * LOADPERVEHICLE + getCurrentLoad() > CAPACITY)
		{
			System.out.println("Unable to add " + count + " vehicle(s). Space available for max. " + ((CAPACITY - getCurrentLoad()) / LOADPERVEHICLE) + " vehicle(s).");
			return false;
		}
		else
		{
			System.out.println(count + " more vehicle(s) now onboard.");
			vehicleCount += count;
			return true;
		}
	}
	
	public boolean removeVehicle(int count)
	{
		if (count > vehicleCount)
		{
			System.out.println("Unable to remove " + count + " vehicle(s). " + vehicleCount + " vehicle(s) onboard.");
			return false;
		}
		else
		{
			System.out.println(count + " vehicle(s) removed.");
			vehicleCount -= count;
			return true;
		}
	}

	public int getCurrentLoad()
	{
		return passengerCount * LOADPERPASSENGER + vehicleCount * LOADPERVEHICLE;
	}
}
