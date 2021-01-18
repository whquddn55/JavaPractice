
public class Rectangle {
	private int leftTopX, leftTopY;
	private int rightBottomX, rightBottomY;
	public Rectangle(final int leftTopX, final int leftTopY, final int rightBottomX, final int rightBottomY) {
		this.leftTopX = leftTopX;
		this.leftTopY = leftTopY;
		this.rightBottomX = rightBottomX;
		this.rightBottomY = rightBottomY;
	}
	
	public void moveBy(final int dx, final int dy) {
		leftTopX += dx;
		leftTopY += dy;
		rightBottomX += dx;
		rightBottomY += dy;
	}
	
	public void print() {
		System.out.printf("(%6d, %6d), (%6d, %6d)%n", leftTopX, leftTopY, rightBottomX, rightBottomY);
	}
}
