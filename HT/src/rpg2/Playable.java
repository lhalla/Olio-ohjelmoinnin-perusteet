package rpg2;

public interface Playable extends Fightable
{
	int MAXHEALTH = 100;
	int POTIONHEAL = 40;
	int SUBHEAL = 30;
	
	int getGold();
	int attack(Hostile target);
	void upgradeAttack(int cost);
	void upgradeDefense(int cost);
}