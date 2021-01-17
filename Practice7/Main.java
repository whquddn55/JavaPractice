package Practice7;

import java.util.*;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	private static ShapeManager manager = new ShapeManager();
	
	private enum OperationKind{ADD, PRINTALL, PRINT, REMOVEALL, TOTALAREA, QUIT};
	public static void main(String[] args) {
		while (true) {
			final String opString = scanner.next();
			final OperationKind op = OperationKind.valueOf(opString.toUpperCase());
			if (op.equals(OperationKind.QUIT))
				break;
			switch(op) {
			case ADD:
				final Shape newShape = manager.createNewShape();
				System.out.println(newShape);
				break;
			case PRINTALL:
				System.out.println(manager);
				break;
			case PRINT:
				System.out.println(manager.getTargetString());
				break;
			case REMOVEALL:
				manager.clear();
				break;
			case TOTALAREA:
				System.out.println(manager.getTotalArea());
				break;
			}
		}
	}
}
