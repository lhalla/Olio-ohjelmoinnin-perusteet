package demo2;

import java.util.UUID;

public class Student extends UniversityPerson
{
	private final UUID studentNo;
	
	public Student(String ssn, String firstName, String lastName)
	{
		super(ssn, firstName, lastName);
		
		this.studentNo = UUID.randomUUID();
	}
	
	public UUID getStudentNo()
	{
		return studentNo;
	}
	
	public String toString()
	{
		return String.format("UUID: %s, StudentNo: %s, SSN: %s, Name: %s", id.toString(), studentNo.toString(), ssn, getName());
	}
}
