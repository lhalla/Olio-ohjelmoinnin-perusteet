package rpg;

/**
 * An interface for fightable creatures that are playable. Used with the player class.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public interface Playable extends Fightable
{
	double MAGACCURACY = 0.80;
	int MAXHEALTH = 100;
	int POTIONHEAL = 40;
	
	int getGold();
	void earnGold(int gold);
	
	void listStats();
	
	void attack(Monster target);
	
	boolean knowsFireball();
	void castFireball(Monster target);
	void learnFireball(int cost);

	int getPotions();
	void addPotion(int cost);
	void drinkPotion();
	
	void upgradeAttack(int step, int cost);
	void upgradeDefense(int step, int cost);
}
