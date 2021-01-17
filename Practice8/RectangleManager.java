package Practice8;

import java.util.*;

public class RectangleManager {
	private ArrayList<MyRectangle> rectangles = new ArrayList<MyRectangle>();
	private Scanner scanner;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RectangleManager manager = new RectangleManager(scanner);
		while(true) {
			System.out.print("Enter a command[create width height, zoom id radio, quit] : ");
			final String command = scanner.next().toLowerCase();
			if (command.equals("create"))
				manager.create();
			else if (command.equals("zoom"))
				manager.zoom();
			else if (command.equals("showall"))
				manager.showAll();
			else if (command.equals("quit")) {
				System.out.println("Bye");
				break;
			}
		}
	}
	
	public RectangleManager(final Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void create() {
		try {
			int width = scanner.nextInt(), height = scanner.nextInt();
			final MyRectangle newRectangle = new MyRectangle(width, height);
			if (width <= 0 || height <= 0) throw new InvalidRectangleException(width, height);
			rectangles.add(newRectangle);
			System.out.println(newRectangle + " is added at " + (rectangles.size() - 1));
		}
		catch(InvalidRectangleException e) {
			System.out.println("사각형의 넓이와 높이는 양수이어야 합니다." + e.getWidth() + " " + e.getHeight());
		}
		catch(InputMismatchException e) {
			System.out.println("입력된 인자의 형식이 맞지 않습니다.");
		}
	}
	
	public void zoom() {
		try {
			int index = scanner.nextInt(), multiple = scanner.nextInt();
			System.out.println("Before : " + rectangles.get(index));
			rectangles.set(index, new MyRectangle(rectangles.get(index).getWidth() * multiple, rectangles.get(index).getHeight() * multiple));
			System.out.println("After : " + rectangles.get(index));
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("존재하지않는 배열의 연소를 접근했습니다." + e.toString());
		}
	}
	
	public void showAll() {
		for (MyRectangle rectangle : rectangles)
			System.out.println(rectangle);
	}
}
