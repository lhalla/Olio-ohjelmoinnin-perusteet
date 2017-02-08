package demo3;

import java.util.LinkedList;
import java.util.Queue;

public class USBTree<T>
{
	private Leaf root;
	private int leaves;

	public USBTree(T data)
	{
		root = new Leaf(data);
		leaves++;
	}

	private class Leaf
	{
		private T data;
		private Leaf left, right;

		public Leaf(T data)
		{
			this.data = data;
		}
	}

	/**
	 * Adds the given data to the first available unoccupied node (Breadth-first).
	 * @param data the data to be added
	 */
	public void add(T data)
	{
		// Set up a queue for the nodes and add the root to it
		Queue<Leaf> queue = new LinkedList<>();
		queue.offer(root);
		
		// Add the data to the first available unoccupied node using a BF algorithm
		while (!queue.isEmpty())
		{
			// Poll the first node in the queue
			Leaf temp = queue.poll();
			
			// If the left branch is occupied, add it to the end of the queue
			if (temp.left != null)
				queue.offer(temp.left);
			// Otherwise add the data to it in a new node
			else
			{
				temp.left = new Leaf(data);
				leaves++;
				return;
			}
			
			// If the right branch is occupied, add it to the end of the queue
			if (temp.right != null)
				queue.offer(temp.right);
			// Otherwise add the data to it in a new node
			else
			{
				temp.right = new Leaf(data);
				leaves++;
				return;
			}
		}
	}

	/**
	 * Returns the data stored in the tree at the requested index.
	 * @param index the index of the data
	 * @return the data stored at the index
	 */
	public T get(int index)
	{
		// Return null if the requested index is out of bounds
		if (index < 0 || leaves <= index)
		{
			System.out.println(String.format("Index out of bounds. Tree size: %s.", size()));
			return null;
		}

		// Temporary variable to keep track of the node
		Leaf current = root;

		// Travel to the requested node
		if (index > 0)
		{
			// Get the route to the requested node
			String route = routeToIndex(index);

			// Travel to the requested node
			for (int i = 0; i < route.length(); i++)
				current = route.charAt(i) == 'L' ? current.left : current.right;
		}

		// Return the data
		return current.data;
	}

	/**
	 * Finds the route from the root to the requested index in a Breadth-first binary tree. L signifies a left and R a right branch.
	 * @param index a requested index
	 * @return the route to the index
	 */
	public static String routeToIndex(int index)
	{
		// Initialize the route
		StringBuilder route = new StringBuilder();

		// Backtrack the route
		while (index > 0)
		{
			// Determine the latest choice of direction and append it to the route
			route.append(index % 2 == 0 ? "R" : "L");

			// Backtrack to the previous node
			index = (index - 1) / 2;
		}

		// Return the route
		return route.reverse().toString();
	}

	/**
	 * Returns the size of the tree.
	 * @return the size of the tree
	 */
	public int size()
	{
		return leaves;
	}
}