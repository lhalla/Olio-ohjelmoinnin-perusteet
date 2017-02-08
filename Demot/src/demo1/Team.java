package demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Team
{
	// Possible player positions
	protected final ArrayList<String> playerPositions = new ArrayList<>(Arrays.asList("Forward", "Back", "Goalkeeper"));
	
	// ArrayList object for storing the available numbers and team members
	protected TreeSet<Integer> availableNumbers;
	protected ArrayList<Player> teamPlayers;

	// Team constructor
	public Team()
	{
		this.availableNumbers = new TreeSet<>();
		this.teamPlayers = new ArrayList<>();
		
		this.availableNumbers.add(1);
	}
	
	/**
	 * Allocates a number for a new player in the team.
	 * @return the allocated number
	 */
	protected int allocateNumber()
	{
		int allocatedNumber = availableNumbers.pollFirst();
		
		if (availableNumbers.isEmpty())
			availableNumbers.add(allocatedNumber + 1);
		
		return allocatedNumber;
	}

	/**
	 * Fires the player with the given number from the team.
	 * @return true if a player with the given number existed
	 */
	public boolean firePlayer(int number)
	{
		if (number < availableNumbers.last() && !availableNumbers.contains(number))
		{
			for (int i = 0; i < teamPlayers.size(); i++)
			{
				if (teamPlayers.get(i).getNumber() == number)
				{
					Player firedPlayer = teamPlayers.remove(i);
					System.out.println(String.format("%s (%d) fired from the position of %s.", firedPlayer.getName(), number, firedPlayer.getPosition()));
					availableNumbers.add(number);
					return true;
				}
			}
		}

		System.out.println(String.format("The team does not have a player with the number %1d.", number));
		return false;
	}

	/**
	 * Hires a new player to the team.
	 * @param name     the name of the player
	 * @param position the position of the player
	 */
	public void hirePlayer(String name, String position)
	{
		if (playerPositions.contains(position))
		{
			int newNumber = allocateNumber();
			teamPlayers.add(newNumber - 1, new Player(name, newNumber, position));
			System.out.println(String.format("%s (%d) hired as a %s.", name, newNumber, position));
		}
		else
			System.out.println(String.format("The team has no positions called %s.", position));
	}

	/**
	 * Prints all information about the team members
	 */
	public void getInfo()
	{
		System.out.println("\nTeam info:");
		for (int i = 0; i < teamPlayers.size(); i++)
			System.out.println(teamPlayers.get(i).toString());
		System.out.println("");
	}
}