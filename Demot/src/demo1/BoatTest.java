package demo1;

public class BoatTest
{
	public static void main(String[] args)
	{
		// Create boats called Sinky and Paddly
		Boat dinghy = new Boat("Sinky", 4.19, 0.787, 20);
		Boat kayak = new Boat("Paddly", 2.5, 0.521, 15);

		// Set the distance to be travelled
		double distance = 18.52;

		// Print the times required for the boats to travel the set distance
		System.out.println("The boat " + dinghy.getName() + " travels a distance of " + distance + " km in " + dinghy.travelTimeKM(distance) + " hours.");
		System.out.println("The boat " + kayak.getName() + " travels a distance of " + distance + " km in " + kayak.travelTimeKM(distance) + " hours.");
	}
}
