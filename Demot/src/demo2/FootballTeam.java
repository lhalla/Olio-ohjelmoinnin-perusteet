package demo2;

import java.util.ArrayList;
import java.util.Arrays;

import demo1.Player;
import demo1.Team;

public class FootballTeam extends Team
{
	// Possible player feet
	private final ArrayList<String> playerFeet = new ArrayList<>(Arrays.asList("Left", "Right"));
	private final int MAXFIELDPLAYERS = 20;
	private final int MAXGOALKEEPERS = 2;
	
	private int goalkeeperCount;
	private int fieldPlayerCount;
	private String homeStadium;
	
	public FootballTeam(String homeStadium)
	{
		super();
		
		this.goalkeeperCount = 0;
		this.homeStadium = homeStadium;
	}
	
	public String getHomeStadium()
	{
		return homeStadium;
	}

	/**
	 * Hires a new player to the team.
	 * @param name     the name of the player
	 * @param position the position of the player
	 * @param foot     the footedness of the player
	 * @param salary   the weekly salary of the player
	 */
	public void hirePlayer(String name, String position, String foot, double salary)
	{
		if (canHire(position, foot))
		{
			int newNumber = allocateNumber();
			teamPlayers.add(newNumber - 1, new FootballPlayer(name, newNumber, position, foot, salary));
			if (position == "Goalkeeper") goalkeeperCount++;
			else                          fieldPlayerCount++;

			System.out.println(String.format("%s (%d) hired as a %s-footed %s with a weekly salary of %s.", name, newNumber, foot.toLowerCase(), position, salary));
		}
	}
	
	/**
	 * Checks if a given player can be hired.
	 * @param position the position of the player
	 * @param foot     the footedness of the player
	 * @return         true if the player can be hired
	 */
	private boolean canHire(String position, String foot)
	{
		if (playerPositions.contains(position) && playerFeet.contains(foot) && canHireField(position) && canHireGoal(position)) return true;
		
		if (!playerPositions.contains(position)) System.out.println(String.format("The team has no positions called %s.", position));
		else if (!canHireField(position))        System.out.println("The maximum number of field players has already been met.");
		else if (!canHireGoal(position))         System.out.println("The maximum number of goalkeepers has already been met.");
		if (!playerFeet.contains(foot))          System.out.println(String.format("The team does not support %s-footed players.", foot.toLowerCase()));
		
		return false;
	}
	
	/**
	 * Checks if a field player can be hired.
	 * @param position the position of the player
	 * @return         true if a field player can be hired
	 */
	private boolean canHireField(String position)
	{
		return !(!position.equals("Goalkeeper") && fieldPlayerCount == MAXFIELDPLAYERS);
	}
	
	/**
	 * Checks if a goalkeeper can be hired.
	 * @param position the position of the player
	 * @return         true if a goalkeeper can be hired
	 */
	private boolean canHireGoal(String position)
	{
		return !(position.equals("Goalkeeper") && goalkeeperCount == MAXGOALKEEPERS);
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

					if (firedPlayer.getPosition().equals("Goalkeeper")) goalkeeperCount--;
					else                                                fieldPlayerCount--;

					return true;
				}
			}
		}

		System.out.println(String.format("The team does not have a player with the number %1d.", number));
		return false;
	}
}