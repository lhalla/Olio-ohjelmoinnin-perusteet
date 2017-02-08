package demo2;

import java.util.ArrayList;
import java.util.UUID;

public class Course
{
	private ArrayList<UniversityPerson> participants;
	private Lecturer lecturer;
	
	private String name;
	private String topic;
	
	private UUID id;
	
	public Course(String name, String topic)
	{
		this.name = name;
		this.topic = topic;
		
		participants = new ArrayList<>();
		id = UUID.randomUUID();
	}
	
	public void addParticipant(UniversityPerson person)
	{
		participants.add(person);
	}
	
	public UUID getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<UniversityPerson> getParticipants()
	{
		return participants;
	}
	
	public String getTopic()
	{
		return topic;
	}
	
	public void removeParticipant(UniversityPerson person)
	{
		participants.remove(person);
	}
	
	public void setLecturer(Lecturer lecturer)
	{
		this.lecturer = lecturer;
	}
	
	public Lecturer getLecturer()
	{
		return lecturer;
	}
	
	public String toString()
	{
		return String.format("Course Name: %s, Topic: %s, Participants: %s.", name, topic, participants.size());
	}
}
