package Practice7;

public class Circle extends Shape {
	private Point center;
	private int radius;
	
	public Circle(final Point center, final int radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return String.format("Circle %s %d %f", center.toString(), radius, getSize());
	}

	@Override
	public double getSize() {
		return radius * radius * Math.PI;
	}

}
