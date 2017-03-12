package rpg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * An event manager for the game. Handles everything that the player can do in the game, from combat
 * to buying upgrades at a vendor.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public class EventManager
{
	private boolean atVendor, inCombat;
	private Scanner reader;
	private HashMap<String, Runnable> combatCommands, generalCommands, vendorCommands;
	private Player player = null;
	private Creature creature = null;

	public EventManager()
	{
		reader = new Scanner(System.in);
		initCommands();

		System.out.print("Do you want to start a new game? YES/no\n: ");

		if (!reader.nextLine().toLowerCase().equals("no"))
			newGame();
		else
		{
			System.out.print("Please enter the name of the save (same as the character's):\n: ");
			loadGame(reader.nextLine());
		}
	}

	/* Command dictionary initialisation */

	/**
	 * Calls for the initialisation of all the command dictionaries.
	 */
	private void initCommands()
	{
		initCombatCommands();
		initGeneralCommands();
		initVendorCommands();
	}

	/**
	 * Initialises the general command dictionary. All eligible commands outside of combat and vendor
	 * are added.
	 */
	private void initGeneralCommands()
	{
		generalCommands = new HashMap<>();

		generalCommands.put("challenge",  () -> fightDragon());
		generalCommands.put("drink potion", () -> player.drinkPotion());
		generalCommands.put("fight", () -> fightMonster());
		generalCommands.put("help", () -> help());
		generalCommands.put("list stats", () -> player.listStats());
		generalCommands.put("quit", () -> quit());
		generalCommands.put("save", () -> saveGame());
		generalCommands.put("shop", () -> enterShop());
		generalCommands.put("sudo gold", () -> sudoGold());
	}

	/**
	 * Initialises the vendor command dictionary. All eligible commands while at vendor are added.
	 */
	private void initVendorCommands()
	{
		vendorCommands = new HashMap<>();
		
		vendorCommands.put("buy attack", () -> ((Vendor) creature).upgradeAttack(player));
		vendorCommands.put("buy defense", () -> ((Vendor) creature).upgradeDefense(player));
		vendorCommands.put("buy potion", () -> ((Vendor) creature).sellPotion(player));
		vendorCommands.put("buy excalibur", () -> ((Vendor) creature).sellExcalibur(player));
		vendorCommands.put("learn fireball", () -> ((Vendor) creature).teachFireball(player));
		vendorCommands.put("help", () -> help());
		vendorCommands.put("leave", () -> exitShop());
		vendorCommands.put("list goods", () -> ((Vendor) creature).listPrices());
		vendorCommands.put("list stats", () -> player.listStats());
	}

	/**
	 * Initialises the combat command dictionary. All eligible commands while in combat are added.
	 */
	private void initCombatCommands()
	{
		combatCommands = new HashMap<>();

		combatCommands.put("attack", () -> player.attack((Monster) creature));
		combatCommands.put("fireball", () -> player.castFireball((Monster) creature));
		combatCommands.put("drink potion", () -> player.drinkPotion());
		combatCommands.put("flee", () -> flee());
		combatCommands.put("help", () -> help());
		combatCommands.put("sudo heal", () -> sudoHeal());
		combatCommands.put("sudo kill", () -> sudoKill());
	}

	/**
	 * Lists the commands available for the player. The output varies based on whether the player is
	 * at a vendor, in combat or neither.
	 */
	private void help()
	{
		if (atVendor)
			((Vendor) creature).help();
		else if (inCombat)
		{
			System.out.println(String.format("%-20s Strike at the enemy.", "attack"));
			System.out.println(String.format("%-20s Drink a potion, restoring %s health.", "drink potion", Playable.POTIONHEAL));
			if(player.knowsFireball()) System.out.println(String.format("%-20s Cast fireball.", "fireball"));
			System.out.println(String.format("%-20s Try to run from the battle.", "flee"));
		}
		else
		{
			System.out.println(String.format("%-20s Challenge the great dragon.", "challenge"));
			System.out.println(String.format("%-20s Drink a potion, restoring 50 health.", "drink potion"));
			System.out.println(String.format("%-20s Encounter a random enemy.", "fight"));
			System.out.println(String.format("%-20s List your current stats.", "list stats"));
			System.out.println(String.format("%-20s Quit the game.", "quit"));
			System.out.println(String.format("%-20s Save your game.", "save"));
			System.out.println(String.format("%-20s Go to the village and enter a shop.", "shop"));
		}
	}

	/**
	 * Prompts the player for a command. This method calls itself to produce a looping prompt.
	 */
	private void getCommand()
	{
		// Read user input
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();
		
		// If input is recognised, execute it, otherwise suggest using the help command
		if (atVendor && vendorCommands.containsKey(input))
				vendorCommands.get(input).run();
		else if (generalCommands.containsKey(input))
				generalCommands.get(input).run();
		else
			System.out.println("Command not recognized. Write 'help' for a list of all commands.");
		
		// Loop the prompt
		getCommand();
	}
	
	/* Combat handling */

	/**
	 * Spawns the final boss.
	 */
	private void fightDragon()
	{
		inCombat = true;
		System.out.println("You climb on top of the nearest mountain and challenge the dragon.");
		creature = new Dragon();
		combat();
	}

	/**
	 * Spawns a random type of enemy, with a one percent chance of spawning the final boss.
	 */
	private void fightMonster()
	{
		inCombat = true;
		System.out.println("You wander about shouting taunts in hopes of finding someone to fight.");

		double rnd = Math.random();
		if (rnd < 0.44) creature = new Goblin();
		else if (rnd < 0.55) creature = new Bear();
		else if (rnd < 0.99) creature = new Orc();
		else creature = new Dragon();
		combat();
	}

	/**
	 * Handles a single round of combat. The player's commands are executed first, after which the enemy
	 * gets to attack.
	 */
	private void combat()
	{
		// Read user input
		System.out.print(": ");
		String input = reader.nextLine().toLowerCase();
		
		// If input is recognised, execute it, otherwise suggest using the help command
		if (combatCommands.containsKey(input))
			combatCommands.get(input).run();
		else
			System.out.println("Command not recognized. Write 'help' for a list of all commands.");
		
		// If the enemy has been killed, end the combat
		if (!creature.isAlive()) endCombat();
		
		// If the player has been killed (through a back-firing fireball), end the game
		if (!player.isAlive()) gameOver();
		
		// If the player didn't use the help command, the enemy attacks
		if (!input.equals("help"))
			((Monster) creature).attack(player);
		
		// If the player has been killed, end the game
		if (!player.isAlive()) gameOver();
		
		// Loop the combat
		combat();
	}
	
	/**
	 * Attempts to run away from the battle. Has a 50 % chance to succeed.
	 */
	private void flee()
	{
		// If the escape was unsuccessful, the combat continues as normal
		if (Math.random() < 0.5)
			System.out.println("You tried to run from the battle but were caught up to.");
		// Otherwise the combat ends and the enemy is despawned
		else
		{
			System.out.println("You managed to get away from the battle.");
			
			inCombat = false;
			creature = null;
			
			getCommand();
		}
	}
	
	/**
	 * Ends the combat and rewards the player after an enemy has been killed.
	 */
	private void endCombat()
	{
		// If the killed enemy was the final boss, the player has won the game
		if (creature instanceof Dragon) winGame();
		
		// Otherwise reward the player with gold
		int reward;
		if (creature instanceof Goblin)
			reward = ((Goblin) creature).getReward();
		else if (creature instanceof Bear)
			reward = ((Bear) creature).getReward();
		else
			reward = ((Orc) creature).getReward();
		
		System.out.println(String.format("You manage to kill the %s and receive %s gold as a reward from the grateful villagers.", creature.getName(), reward));
		
		player.earnGold(reward);
		creature = null;
		inCombat = false;
		
		getCommand();
	}
	
	/* Vendor handling */
	
	/**
	 * Spawns a vendor.
	 */
	private void enterShop()
	{
		atVendor = true;
		creature = new Vendor();
		System.out.println(String.format("You go to the village and find %s.", creature.getName()));
		System.out.println("\"Greetings, adventurer, how can I be of service?\"");
	}
	
	/**
	 * Despawns the vendor.
	 */
	private void exitShop()
	{
		creature = null;
		atVendor = false;
		System.out.println("You leave the shop.");
	}

	/* Session handling */

	/**
	 * Starts a new game. Prompts the player for a character name without special characters in order
	 * to provide a valid filename for a save.
	 */
	private void newGame()
	{
		String input = "";

		// Asks the user for a name for their character until a viable name is entered
		while (input.length() < 1)
		{
			System.out.print("Please enter your name (special characters are ignored)\n: ");
			input = reader.nextLine().replaceAll("[^A-Za-z0-9 ]", "").trim();
		}

		player = new Player(input);
		System.out.println(String.format("Welcome to the wondrous world of Objectia, %s!", input));
		System.out.println("The realm is in shambles due to the recent appearance of a terrifying dragon. Will you be the one to save us all?");

		getCommand();
	}

	/**
	 * Saves the current state of the player's character.
	 */
	private void saveGame()
	{
		// Save the game to a file with the same name as the character. Any previously existing files will be overwritten
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(player.getName().toLowerCase() + ".save")))
		{
			oos.writeObject(player);
			System.out.println(String.format("Game successfully saved to %s.save.", player.getName().toLowerCase()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Loads a saved game with the specified character name.
	 * @param saveName name of the played character in the save.
	 */
	private void loadGame(String saveName)
	{
		// Attempts to load a saved game using the specified name
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveName.toLowerCase() + ".save")))
		{
			this.player = (Player) ois.readObject();
			System.out.println(String.format("Welcome back, %s!", player.getName()));
			
			getCommand();
		}
		// If no such save is found, a new game is started instead
		catch (FileNotFoundException e)
		{
			System.out.println("Save not found. Starting a new game...");
			
			newGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Quits the game with an option to save.
	 */
	private void quit()
	{
		// Asks the user if they wish to save the game before quitting
		System.out.print("Do you want to save the game before quitting? yes/NO\n: ");
		if (reader.nextLine().toLowerCase().equals("yes"))
			saveGame();

		System.out.println(String.format("The realm hopes to welcome you again soon, %s!", player.getName()));

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}

		System.exit(0);
	}
	
	/**
	 * Ends the game when the player has won the game.
	 */
	private void winGame()
	{
		System.out.println(String.format("Congratulations, %s, you have successfully defeated %s and saved the realm from its grip of terror!", player.getName(), creature.getName()));
		System.out.println("As a token of gratitude, the villagers have baked you a cake.");
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}

		System.exit(0);
	}
	
	/**
	 * Ends the game when the player has been killed.
	 */
	private void gameOver()
	{
		System.out.println("You have been killed! May you have better luck in your future lives!");
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}
		
		System.exit(0);
	}
	
	/* Cheats (for testing purposes only, of course) */
	
	/**
	 * Gives the player 1000 gold.
	 */
	private void sudoGold()
	{
		player.earnGold(1000);
	}
	
	/**
	 * Fully heals the player.
	 */
	private void sudoHeal()
	{
		player.takeDamage(-Playable.MAXHEALTH);
	}
	
	/**
	 * Instantly kills the enemy.
	 */
	private void sudoKill()
	{
		((Monster) creature).takeDamage(((Monster) creature).getHealth());
	}
}
