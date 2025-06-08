package Bank;

public class Cylinder extends Shape{
	private double radius;
	private double height;

	public Cylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}
	@Override
	public double surface_Area() {
		return 2 * Math.PI * radius * (radius+ height);
	}
	@Override
	public double volume() {
		return Math.PI * Math.pow(radius, 2) * height;
	}
	@Override
	public String toString() {
		return "Cylinder:\nSurface Area: " + surface_Area() + "\nVolume: " + volume();
	}
}
