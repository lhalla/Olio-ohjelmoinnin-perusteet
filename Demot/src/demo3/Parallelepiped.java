package demo3;

public class Parallelepiped implements Item
{
	private Point3D centre;
	private Point3D v1;
	private Point3D v2;
	private Point3D v3;
	
	private double sideLength;
	
	public Parallelepiped(Point3D centre, double sideLength)
	{
		this.centre = centre;
		this.sideLength = sideLength;
	}
	
	public Parallelepiped(double x, double y, double z, double sideLength)
	{
		this(new Point3D(x,y,z), sideLength);
	}
	
	public Parallelepiped(Point3D corner, Point3D v1, Point3D v2, Point3D v3)
	{
		this.centre = corner;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	@Override
	public double getArea()
	{
		if (sideLength == 0.0)
		{
			return 2 * (v1.crossProduct(v2).norm() + v1.crossProduct(v3).norm() + v2.crossProduct(v3).norm());
		}
		else
		{
			return 6 * sideLength * sideLength;
		}
	}

	@Override
	public Point3D getCentre()
	{
		if (sideLength == 0.0)
		{
			return centre.add(v1.multiply(0.5)).add(v2.multiply(0.5)).add(v3.multiply(0.5));
		}
		else
		{
		return centre;
		}
	}
	
	@Override
	public String toString()
	{
		if (sideLength == 0.0)
		{
			return String.format("Corner: %s, vector 1: %s, vector 2: %s, vector 3: %s", centre.toString(), v1.toString(), v2.toString(), v3.toString());
		}
		else
		{
			return String.format("Centre: %s, side length: %s", centre.toString(), sideLength);
		}
	}
}
