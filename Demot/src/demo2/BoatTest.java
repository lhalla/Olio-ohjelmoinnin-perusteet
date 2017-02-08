package demo2;

public class BoatTest
{
	public static void main(String[] args)
	{
		CargoBoat cargoy = new CargoBoat("Heavy", 10, 1.5, 40, 100);
		System.out.println(cargoy.travelTimeKM(10));

		cargoy.addCargo(60);
		System.out.println(cargoy.travelTimeKM(10));
		cargoy.addCargo(50);
		cargoy.removeCargo(100);
		
		FerryBoat ferroy = new FerryBoat("Lifty", 10, 1.5, 40, 99);
		
		ferroy.addVehicle(5);
		ferroy.removeVehicle(4);
		
		ferroy.addPassenger(5);
		ferroy.addPassenger(20);
		ferroy.removePassenger(3);
		ferroy.removePassenger(30);
	}
}
