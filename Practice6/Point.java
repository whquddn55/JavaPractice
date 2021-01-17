package Practice6;

public class Point {
	private int x, y;
	
	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "[" + x + ", " + y + ']';
	}
	
	public int getDistanceTo(final Point other) {
		return (int)Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
	}
}
