package demo2;

public class DoubleChainTest
{
	public static void main(String[] args)
	{
		System.out.println("Creating an Integer list...");
		DoubleChain<Integer> listInteger = new DoubleChain<>();
		
		System.out.println("Adding values 1-7 to the list...");
		listInteger.add(1);
		listInteger.add(2);
		listInteger.add(3);
		listInteger.add(4);
		listInteger.add(5);
		listInteger.add(6);
		listInteger.add(7);
		
		System.out.println("Removing the 4th item...");
		listInteger.remove(3);
		
		System.out.println("Removing '2' from the list...");
		listInteger.removeValue(2);
		
		System.out.println("Adding the value 42 to the head, 3rd, and tail indices...");
		listInteger.add(0, 42);
		listInteger.add(4, 42);
		listInteger.add(6, 42);
		
		System.out.println("The current list forward: " + listInteger.toStringFW());
		System.out.println("The current list backward: " + listInteger.toStringBW());
		
		System.out.println("");
		
		System.out.println("Creating a String list...");
		DoubleChain<String> listString = new DoubleChain<>();
		
		System.out.println("Adding strings 'mango', 'pineapple', 'banana', 'maracuja' and 'kiwi' to the list...");
		listString.add("mango");
		listString.add("pineapple");
		listString.add("banana");
		listString.add("maracuja");
		listString.add("kiwi");
		
		System.out.println("Removing the 4th item...");
		listString.remove(3);
		
		System.out.println("Removing 'mango' from the list...");
		listString.removeValue("mango");
		
		System.out.println("Adding the value 'Belgium' to the head, 1st, and tail indices...");
		listString.add(0, "Belgium");
		listString.add(2, "Belgium");
		listString.add(4, "Belgium");
		
		System.out.println("The current list forward: " + listString.toStringFW());
		System.out.println("The current list backward: " + listString.toStringBW());
	}
}
