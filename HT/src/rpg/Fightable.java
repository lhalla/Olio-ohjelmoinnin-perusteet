package rpg;

/**
 * An interface for creatures that are fightable, and thus can be killed.
 * @author Lauri Halla-aho, Mikael Remes
 *
 */
public interface Fightable
{
	double ACCURACY = 0.95;
	double CRITICAL = 0.05;
	
	int getAttack();
	int getDefense();
	int getHealth();
	
	void takeDamage(int dmg);
}
