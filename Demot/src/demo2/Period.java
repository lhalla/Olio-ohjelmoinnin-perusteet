package demo2;

import java.util.ArrayList;

public class Period
{
	private ArrayList<Course> courses;
	private int periodNo;
	
	public Period(int number)
	{
		this.periodNo = number;
		
		courses = new ArrayList<>();
	}
	
	public void addCourse(String name, String topic)
	{
		courses.add(new Course(name, topic));
	}
	
	public void addCourse(Course course)
	{
		courses.add(course);
	}
	
	public Course getCourse(String name)
	{
		for (Course course : courses)
		{
			if (course.getName().equals(name)) return course;
		}
		
		return null;
	}
	
	public ArrayList<Course> getCourses()
	{
		return courses;
	}
	
	public int getPeriodNo()
	{
		return periodNo;
	}
	
	public void removeCourse(String name)
	{
		for (int i = 0; i < courses.size(); i++)
		{
			if (courses.get(i).getName().equals(name)) courses.remove(i);
		}
	}
	
	public void removeCourse(Course course)
	{
		courses.remove(course);
	}

	public String toString()
	{
		return String.format("Period No: %s, Courses: %s.", periodNo, courses.size());
	}
}