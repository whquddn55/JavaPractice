package Practice7;

public class Rectangle extends Shape {
	int height, width;
	
	public Rectangle(final int width, final int height) {
		this.width = width; this.height = height;
	}

	@Override
	public String toString() {
		return String.format("Rectangle %d %d %f", width, height, getSize());
	}

	@Override
	public double getSize() {
		return height * width;
	}

}
