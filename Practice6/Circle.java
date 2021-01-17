package Practice6;

public class Circle implements MyComparable{
	private Point center;
	private int radius;
	
	public Circle(final Point center, final int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public int compareTo(final MyComparable other) {
		return other.getSize() == getSize() ? 0 : (getSize() < other.getSize() ? -1 : 1);
	}
	
	public String toString() {
		return "[" + center + ' ' + radius + ' '  + getSize() + ']';
	}
	
	public int getSize() {
		return (int)(radius * radius * Math.PI);
	}
}
