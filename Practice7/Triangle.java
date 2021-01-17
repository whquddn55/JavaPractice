package Practice7;

public class Triangle extends Shape {
	private int width, height;
	
	public Triangle(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String toString() {
		return String.format("Triangle %d %d %f", width, height, getSize());
	}

	@Override
	public double getSize() {
		return width * height * 0.5;
	}

}
