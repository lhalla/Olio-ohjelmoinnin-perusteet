package demo2;

import java.util.UUID;

public class UniversityPerson
{
	protected final UUID id;
	
	protected String firstName;
	protected String lastName;
	protected String ssn;
	
	public UniversityPerson(String ssn, String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.id = UUID.randomUUID();
	}
	
	public UUID getID()
	{
		return id;
	}
	
	public String getName()
	{
		return lastName + ", " + firstName;
	}
	
	public String getSSN()
	{
		return ssn;
	}
	
	public void setName(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString()
	{
		return String.format("UUID: %s, SSN: %s, Name: %s", id.toString(), ssn, getName());
	}
}