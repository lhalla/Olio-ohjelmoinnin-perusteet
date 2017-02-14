package rpg2;

public interface Fightable
{
	double ACCURACY = 0.95;
	double CRITICAL = 0.05;
	
	int getAttack();
	int getDefense();
	int getHealth();
	double getAccuracy();
	double getCritical();
	boolean takeDamage(int dmg);
}
