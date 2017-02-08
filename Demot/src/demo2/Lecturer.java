package demo2;

public class Lecturer extends UniversityPerson
{
	private double salary;
	
	public Lecturer(String ssn, String firstName, String lastName, double salary)
	{
		super(ssn, firstName, lastName);
		
		this.salary = salary;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	public String toString()
	{
		return String.format("UUID: %s, SSN: %s, Name: %s, Salary: %s", id.toString(), ssn, getName(), salary);
	}
}
