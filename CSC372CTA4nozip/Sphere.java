package Bank;

public class Sphere extends Shape{
	private double radius;
	
	public Sphere(double radius) {
		this.radius = radius;	
	}
	@Override
	public double surface_Area() {
		return 4 * Math.PI * Math.pow(radius, 2);
	}
	@Override
	public double volume() {
		return (4/3) * Math.PI * Math.pow(radius, 3);
	}
	@Override 
	public String toString() {
		return "Sphere:\nSurface Area: " + surface_Area() + "\nVolume: " + volume();
	}
}
