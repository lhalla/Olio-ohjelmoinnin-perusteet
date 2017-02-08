package demo2;

public class DoubleChain<T>
{
	// Link objects pointing to the first and last items on the chain
	private Link head, tail;

	// The length of the chain, i.e. the number of items on it
	private int chainLength;

	// Chain constructor
	public DoubleChain()
	{
		this.head = null;
		this.tail = null;
		this.chainLength = 0;
	}

	// An inner class for the "links" in the chain
	private class Link
	{
		// The value of the link
		private T value;

		// Link object pointing to the previous and next links in the chain
		private Link prev;
		private Link next;

		// Link constructor
		public Link(T value, Link prev, Link next)
		{
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	/**
	 * Returns the value stored in the link at the requested index.
	 * @param index int in [0, chainLength)
	 * @return T, the value of the link
	 */
	public T get(int index)
	{
		// If the index is outside of the chain, throw an IndexOutOfBoundsException
		if (index < 0 || chainLength <= index)
			throw new IndexOutOfBoundsException();

		// Link object used to walk through the chain until the requested index is reached
		Link current = head;

		// Index counter
		int counter = 0;

		// Walk through the chain until the requested index is reached
		while (counter++ < index)
			current = current.next;

		return current.value;
	}

	/**
	 * Replaces the value of the link at the requested index with the given value.
	 * @param index int in [0, chainLength)
	 * @param value T
	 * @return T, the old value of the link
	 */
	public T set(int index, T value)
	{
		// If the index is outside of the chain, throw an IndexOutOfBoundsException
		if (index < 0 || chainLength <= index)
			throw new IndexOutOfBoundsException();

		// Link object used to walk through the chain until the requested index is reached
		Link current = head;

		// Index counter
		int counter = 0;

		// Variable used to store the link's old value
		T oldValue;

		// Walk through the chain until the requested index is reached
		while (counter++ < index)
			current = current.next;

		// Store the old value of the link and replace it with the given new value
		oldValue = current.value;
		current.value = value;

		return oldValue;
	}

	/**
	 * Adds the given value to a new link at the end of the chain.
	 * @param value T
	 */
	public void add(T value)
	{
		// If the chain is not empty, add the new link at the end of the chain
		if (!isEmpty())
		{
			// Link object to point to the old tail
			Link oldTail = tail;

			// Create a new link object with the given value
			tail = new Link(value, oldTail, null);

			// Point the old tail link to the newly created tail
			oldTail.next = tail;
		}
		// If the chain is empty, add the new link as the head and the tail
		else
		{
			head = new Link(value, null, null);
			tail = head;
		}

		// Increment the chain length
		chainLength++;
	}

	/**
	 * Adds the given value at the requested index.
	 * @param index in [0, chainLength]
	 * @param value T
	 */
	public void add(int index, T value)
	{
		// If the index is outside of the chain, throw an IndexOutOfBoundsException
		if (index < 0 || chainLength < index)
			throw new IndexOutOfBoundsException();

		// If the requested index is immediately after the last link of the chain, simply add a new link at the end
		if (index == chainLength)
			this.add(value);
		// If the requested index is at the beginning of the chain, add it before the current head
		else if (index == 0)
		{
			// Link object to point to the old head
			Link oldHead = head;

			// Create a new head link that points to the old head
			head = new Link(value, null, head);

			// Point the old head link to the new head
			oldHead.prev = head;

			// Increment the chain length
			chainLength++;
		}
		// Otherwise place the new link between two other links
		else
		{
			// Link objects to point to current and previous links to allow the insertion of a link between them
			Link previous = head;
			Link current = head;

			// Index counter
			int counter = 0;

			// Walk through the chain until the requested index is reached
			while (counter++ < index)
			{
				previous = current;
				current = previous.next;
			}

			// Point the previous link at a newly created link with the given value and point the new link to the old
			// link at the requested index
			previous.next = new Link(value, previous, current);
			current.prev = previous.next;

			// Increment the chain length
			chainLength++;
		}
	}

	/**
	 * Removes a link from the requested index.
	 * @param index int in [0, chainLength)
	 * @return boolean, true if a link was successfully removed, false otherwise
	 */
	public boolean remove(int index)
	{
		// Links can not be removed from an empty list
		if (isEmpty())
			System.out.println("Unable to remove an element from an empty list.");
		// If the index is outside of the chain, throw an IndexOutOfBoundsException
		else if (index < 0 || chainLength <= index)
			throw new IndexOutOfBoundsException();
		// Proceed to remove a link
		else
		{
			// Link objects to point to the previous and current links to allow the removal of the requested link
			Link previous = head;
			Link current = head;

			// Index counter
			int counter = 0;

			// Walk through the chain until the requested index is reached
			while (counter++ < index)
			{
				previous = current;
				current = previous.next;
			}

			// If the chain has only one link, both the head and tail have to point to null
			if (chainLength == 1)
			{
				head = null;
				tail = null;
			}
			// If the head link is to be removed, point the head to the second link
			else if (current == head)
			{
				head = head.next;
				head.prev = null;
			}
			// If the tail link is to be removed, point the prior link to null
			else if (current == tail)
			{
				tail = previous;
				tail.next = null;
			}
			// Otherwise point the previous link to the one following the link to be deleted
			else
			{
				previous.next = current.next;
				previous.next.prev = previous;
			}

			// Decrement the chain length
			chainLength--;
			return true;
		}

		return false;
	}

	/**
	 * Removes the first link with the given value from the chain.
	 * @param value T
	 * @return boolean,  true if a link was successfully removed, false otherwise
	 */
	public boolean removeValue(T value)
	{
		// Links can not be removed from an empty list
		if (isEmpty())
			System.out.println("Unable to remove an element from an empty list.");
		// Proceed to remove a link
		else
		{
			// Link objects to point to the previous and current links to allow the removal of the requested link 
			Link previous = head;
			Link current = head;

			// Walk through the chain to find a link with the given value
			while (current != null)
			{
				// If the current link has the given value, remove it
				if (current.value == value)
				{
					// If the chain has only one link, both the head and the tail have to point to null
					if (chainLength == 1)
					{
						head = null;
						tail = null;
					}
					// If the head link is to be removed, point the head to the second link
					else if (current == head)
					{
						head = head.next;
						head.prev = null;
					}
					// If the tail link is to be removed, point the prior link to null
					else if (current == tail)
					{
						tail = previous;
						tail.next = null;
					}
					// Otherwise point the previous link to the one following the link to be deleted
					else
					{
						previous.next = current.next;
						previous.next.prev = previous;
					}

					// Decrement the chain length
					chainLength--;
					return true;
				}

				// Move forward in the chain
				previous = current;
				current = previous.next;
			}
		}

		return false;
	}

	/**
	 * Returns true if the given value is in the chain.
	 * @param value T
	 * @return boolean, true if the value is contained in the chain, false otherwise
	 */
	public boolean contains(T value)
	{
		// Link object used to walk through the chain
		Link current = head;

		// Walk through the chain until a matching value is found or the end is reached
		while (chainLength > 0 && current != null)
		{
			// If the current link's value is equal to the given value, return true
			if (current.value == value)
				return true;
			// Otherwise move to the next link in the chain
			else
				current = current.next;
		}

		return false;
	}

	/**
	 * Finds the index of the first occurrence of the given value in the chain. Returns -1 if the value is not found in
	 * the chain.
	 * @param value T
	 * @return int, the index of the first occurrence of the value in the chain or -1 if not found
	 */
	public int find(T value)
	{
		// Link object used to walk through the chain
		Link current = head;

		// Index counter
		int counter = 0;

		// Walk through the chain until a link with the given value is found or the end of the chain is reached
		while (counter++ < chainLength)
		{
			// If the current link's value is equal to the given value, return the current index
			if (current.value == value)
				return counter - 1;
			// Otherwise proceed to the next link
			else
				current = current.next;
		}

		return -1;
	}

	/**
	 * Returns the length of the chain.
	 * @return int, the length of the chain
	 */
	public int size()
	{
		return chainLength;
	}

	/**
	 * Returns true if the chain is empty.
	 * @return boolean, true if the chain is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return chainLength == 0;
	}

	/**
	 * Prints the values contained in the links of the chain, separated with spaces, as a string.
	 * @return String, a space-separated string containing all the values of the links of the chain
	 */
	public String toStringFW()
	{
		// StringBuilder object for storing the values of the links
		StringBuilder stringList = new StringBuilder();

		// Link object used to walk through the chain
		Link current = head;
		
		stringList.append("null -> ");

		// Walk through the chain
		while (current != null)
		{
			// Append the value of the link to the end of the string
			stringList.append(current.value + " -> ");

			// Proceed to the next link
			current = current.next;
		}
		
		stringList.append("null");

		return stringList.toString();
	}
	
	public String toStringBW()
	{
		// StringBuilder object for storing the values of the links
		StringBuilder stringList = new StringBuilder();

		// Link object used to walk through the chain
		Link current = tail;
		
		stringList.append("null -> ");

		// Walk through the chain
		while (current != null)
		{
			// Append the value of the link to the end of the string
			stringList.append(current.value + " -> ");

			// Proceed to the next link
			current = current.prev;
		}
		
		stringList.append("null");

		return stringList.toString();
	}
}