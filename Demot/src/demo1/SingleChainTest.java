package demo1;

public class SingleChainTest
{
	public static void main(String[] args)
	{
		// Create a chain
		SingleChain list = new SingleChain();

		// Test the functionality of printing the contents of an empty chain and the size method
		System.out.println("Printout of the newly created chain: " + list.toString() + ". Length of the chain: " + list.size());

		// Add the values [1,10] to the chain
		for (int i = 0; i < 10; i++)
			list.add(i + 1);

		// Test the functionality of printing the contents of a non-empty chain
		System.out.println("Printout of the chain after adding integers 1-10 to it: " + list.toString() + ". Length of the chain: " + list.size());

		// Test the functionality of removing a link at a given index
		list.remove(3);
		System.out.println("Printout of the chain after removing the 4th item: " + list.toString() + ". Length of the chain: " + list.size());

		// Test the functionality of removing a given value from the chain
		list.removeValue(10);
		System.out.println("Printout of the chain after removing 10: " + list.toString() + ". Length of the chain: " + list.size());

		// Test the functionality of adding a given value at a given index
		list.add(4, -2);
		System.out.println("Printout of the chain after adding -2 as the 5th item: " + list.toString() + ". Length of the chain: " + list.size());

		// Test the functionality of finding a given value
		System.out.println("The value 7 is located at index: " + list.find(7) + ". The value -3 is located at index: " + list.find(-3));

		// Test the functionality of checking if the chain contains values
		System.out.println("The chain contains the value 7: " + list.contains(7) + ". The chain contains the value -3: " + list.contains(-3));
	}
}
