package demo2;

public class AcademicYear
{
	private int year;
	private Period[] periods = new Period[5];
	
	public AcademicYear(int year)
	{
		this.year = year;
		
		for (int i = 1; i <= periods.length; i++)
		{
			periods[i - 1] = new Period(i);
		}
	}
	
	public Period getPeriod(int number)
	{
		return periods[number - 1];
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void printInfo()
	{
		System.out.println(String.format("\nInfo about the academic year %s.", year));
		for (int i = 0; i < periods.length; i++)
		{
			System.out.println(periods[i].toString());
			
			if (periods[i].getCourses().size() > 0)
			{
				for (Course course : periods[i].getCourses()) System.out.println("  " + course.toString());
			}
		}
	}
}
