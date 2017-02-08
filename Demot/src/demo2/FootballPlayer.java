package demo2;

import demo1.Player;

public class FootballPlayer extends Player
{
	private String foot;
	private double salary;
	
	public FootballPlayer(String name, int number, String position, String foot, double salary)
	{
		super(name, number, position);
		
		this.foot = foot;
		this.salary = salary;
	}
	
	public String getFoot()
	{
		return foot;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setFoot(String foot)
	{
		if (foot == "Right" || foot == "Left")
		{
			System.out.println(name + " successfully set as " + foot.toLowerCase() + "-footed.");
			this.foot = foot;
		}
		else
			System.out.println(name + " has no " + foot.toLowerCase() + " foot.");
	}
	
	public void setSalary(double salary)
	{
		System.out.println(name + "'s salary successfully set as " + salary);
		this.salary = salary;
	}
	
	@Override
	public String toString()
	{
		return String.format("%2d: %s, %s, %s, %s", number, name, position, foot, salary);
	}
}
