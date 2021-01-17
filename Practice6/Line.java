package Practice6;

public class Line implements MyComparable {
	private Point p1, p2;
	
	public Line(final Point p1, final Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public int compareTo(final MyComparable other) {
		return other.getSize() == getSize() ? 0 : (getSize() < other.getSize() ? -1 : 1);
	}
	
	public String toString() {
		return "[" + p1 + " " + p2 + ' ' + getSize() + "]";
	}
	
	public int getSize() {
		return p1.getDistanceTo(p2);
	}
}
