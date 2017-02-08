package demo2;

import demo1.Boat;

public class CargoBoat extends Boat
{
	protected final double CAPACITY;
	protected double cargoOnboard = 0.0;
	
	public CargoBoat(String name, double length, double draft, double speed, double capacity)
	{
		super(name, length, draft, speed);
		
		this.CAPACITY = capacity;
	}
	
	public boolean addCargo(double cargo)
	{
		if (cargoOnboard + cargo > CAPACITY)
		{
			System.out.println("Unable to add " + cargo + " unit(s) of cargo. Space available for max. " + (CAPACITY - cargoOnboard) + " unit(s).");
			return false;
		}
		else
		{
			System.out.println(cargo + " more unit(s) of cargo now onboard.");
			cargoOnboard += cargo;
			return true;
		}
	}
	
	public boolean removeCargo(double cargo)
	{
		if (cargoOnboard == 0)
		{
			System.out.println("Cargo removal failed. The cargo ship is already empty.");
			return false;
		}
		else if (cargoOnboard - cargo <= 0)
		{
			System.out.println("Unable to remove " + cargo + " unit(s) of cargo. " + cargoOnboard + " unit(s) of cargo onboard.");
			return false;
		}
		else
		{
			cargoOnboard -= cargo;
			return true;
		}
	}
	
	/**
	 * Returns the time taken, in hours, to travel a given distance, in nautical miles.
	 * @param distance double
	 * @return double, the number of hours required to travel the distance
	 */
	@Override
	public double travelTimeNM(double nauticalMiles)
	{
		return nauticalMiles / (speed * (1 - 0.1*(int)(10 * cargoOnboard / CAPACITY)));
	}

	/**
	 * Returns the time taken, in hours, to travel a given distance, in kilometres.
	 * @param distance double
	 * @return double, the number of hours required to travel the distance
	 */
	@Override
	public double travelTimeKM(double kilometres)
	{
		return kilometres / (1.852 * speed * (1 - 0.1*(int)(10 * cargoOnboard / CAPACITY))); 
	}
}