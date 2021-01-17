package Practice7;

import java.util.*;

public class ShapeManager {
	private List<Shape> list = new ArrayList<Shape>();
	private Scanner scanner = Main.scanner;
	
	public Shape createNewShape() {
		final char type = scanner.next().toUpperCase().charAt(0);
		Shape newShape = null;
		switch(type) {
		case 'T':
			newShape = new Triangle(scanner.nextInt(), scanner.nextInt());
			break;
		case 'C':
			newShape = new Circle(new Point(scanner.nextInt(), scanner.nextInt()), scanner.nextInt());
			break;
		case 'R':
			newShape = new Rectangle(scanner.nextInt(), scanner.nextInt());
			break;
		}
		list.add(newShape);
		return newShape;
	}
	
	public String toString() {
		StringBuilder msg = new StringBuilder();
		for (Shape shape : list) {
			msg.append(shape);
			msg.append('\n');
		}
		if (msg.equals(""))
			msg.append("NONE");
		return msg.toString();
	}
	
	public String getTargetString() {
		final char type = scanner.next().toUpperCase().charAt(0);
		StringBuilder msg = new StringBuilder();
		switch(type) {
		case 'T' :
			for (Shape shape : list) {
				if (shape instanceof Triangle) {
					msg.append(shape);
					msg.append('\n');
				}
			}
			break;
		case 'C' :
			for (Shape shape : list) {
				if (shape instanceof Circle) {
					msg.append(shape);
					msg.append('\n');
				}
			}
			break;
		case 'R' :
			for (Shape shape : list) {
				if (shape instanceof Rectangle) {
					msg.append(shape);
					msg.append('\n');
				}
			}
			break;
		}
		if (msg.isEmpty())
			msg.append("NONE");
		return msg.toString();
	}
	
	public void clear() {
		System.out.println(list.size());
		list.clear();
	}
	
	public double getTotalArea() {
		double result = 0.0;
		for (Shape shape : list)
			result += shape.getSize();
		return result;
	}
}
