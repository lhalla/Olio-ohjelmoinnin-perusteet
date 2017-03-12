package rpg;

/**
 * An interface for fightable creatures that are hostile. Used with all of the enemies.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public interface Hostile extends Fightable
{
	int MINATTACK = 10;
	int RANGEATTACK = 6;
	int MINDEFENSE = 2;
	int RANGEDEFENSE = 2;
	int MAXHEALTH = 30;

	int getReward();
	
	void attack(Player target);
}
