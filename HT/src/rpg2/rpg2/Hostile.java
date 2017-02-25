package rpg2;

public interface Hostile extends Fightable
{
	int MAXHEALTH = 25;
	int MINATTACK = 5;
	int RANGEATTACK = 6;
	int MINDEFENSE = 3;
	int RANGEDEFENSE = 4;
	
	int GOLDREWARD = 15;
	
	int attack(Playable target);
}