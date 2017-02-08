package demo2;

public class CourseManagerTest
{
	public static void main(String[] args)
	{
		AcademicYear ay = new AcademicYear(2017);
		
		ay.getPeriod(1).addCourse("Tietojenkäsittelyn perusteet","Python");
		ay.getPeriod(1).addCourse("Insinöörityön perusteet","LEGO");
		ay.getPeriod(1).addCourse("Insinöörimatematiikka A","Joukot");
		
		ay.getPeriod(2).addCourse("Insinöörimatematiikka B","Derivaatat");
		
		ay.getPeriod(3).addCourse("Insinöörimatematiikka D","Lineaarialgebra");

		ay.getPeriod(4).addCourse("Insinöörimatematiikka C","Fourier");
		
		ay.printInfo();
	}
}
