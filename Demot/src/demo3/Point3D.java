package demo3;

public class Point3D
{
	private double x;
	private double y;
	private double z;
	
	public Point3D()
	{
		this(0.0, 0.0, 0.0);
	}

	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setZ(double z)
	{
		this.z = z;
	}
	
	public Point3D add(double addition)
	{
		return new Point3D(x + addition, y + addition, z + addition);
	}
	
	public Point3D add(Point3D addition)
	{
		return new Point3D(x + addition.getX(), y + addition.getY(), z + addition.getZ());
	}
	
	public Point3D crossProduct(Point3D cross)
	{
		return new Point3D(y * cross.getZ() - z * cross.getY(), z * cross.getX() - x * cross.getZ(), x * cross.getY() - y * cross.getX());
	}
	
	public double dotProduct(Point3D dot)
	{
		return x * dot.getX() + y * dot.getY() + z * dot.getZ();
	}
	
	public Point3D multiply(double multiplier)
	{
		return new Point3D(multiplier * x, multiplier * y, multiplier * z);
	}
	
	public Point3D subtract(double subtraction)
	{
		return new Point3D(x - subtraction, y - subtraction, z - subtraction);
	}
	
	public Point3D subtract(Point3D subtraction)
	{
		return new Point3D(x - subtraction.getX(), y - subtraction.getY(), z - subtraction.getZ());
	}
	
	public double norm()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	@Override
	public boolean equals(Object o)
	{
		Point3D p = (Point3D) o;
		return (x == p.getX()) && (y == p.getY()) && (z == p.getZ());
	}
	
	@Override
	public String toString()
	{
		return String.format("(%s, %s, %s)", x, y, z);
	}
}