package demo2;

public class CourseManagerTest
{
	public static void main(String[] args)
	{
		AcademicYear ay = new AcademicYear(2017);
		
		ay.getPeriod(1).addCourse("Tietojenk�sittelyn perusteet","Python");
		ay.getPeriod(1).addCourse("Insin��rity�n perusteet","LEGO");
		ay.getPeriod(1).addCourse("Insin��rimatematiikka A","Joukot");
		
		ay.getPeriod(2).addCourse("Insin��rimatematiikka B","Derivaatat");
		
		ay.getPeriod(3).addCourse("Insin��rimatematiikka D","Lineaarialgebra");

		ay.getPeriod(4).addCourse("Insin��rimatematiikka C","Fourier");
		
		ay.printInfo();
	}
}
