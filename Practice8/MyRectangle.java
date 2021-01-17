package Practice8;

public class MyRectangle {
	private int width, height;
	
	public MyRectangle(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public String toString() {
		return "Rectangle : width " + width + ", height " + height;
	}
}
