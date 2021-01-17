package Practice6;

import java.util.*;

public class Main {
	private enum OperationKind{ADD_C, ADD_L, LIST, CLEAR, SORT_A, SORT_D, QUIT, INVALID};
	private enum SortKind{ASCENDING, DESCENDING};
	
	private static Scanner scanner = new Scanner(System.in);
	private static List<MyComparable> comparableList = new ArrayList<MyComparable>();
	public static void main(String[] args) {
		while(true) {
			System.out.print("Enter Operation String ! ");
			final OperationKind op = OperationKind.valueOf(scanner.next().toUpperCase());
			if (op == OperationKind.QUIT) {
				System.out.println("Bye");
				break;
			}
			else if (op == OperationKind.INVALID) {
				System.out.println("Invalid Input!");
				break;
			}
			
			switch(op) {
			case ADD_C:
				final Circle newCircle = createCircle();
				System.out.println(newCircle);
				break;
			case ADD_L:
				final Line newLine = createLine();
				System.out.println(newLine);
				break;
			case SORT_A:
				sortList(comparableList, SortKind.ASCENDING);
				break;
			case SORT_D:
				sortList(comparableList, SortKind.DESCENDING);
				break;
			case LIST:
				System.out.println(comparableList);
				break;
			case CLEAR:
				comparableList.clear();
				break;
				
			}
		}
	}
	
	private static Circle createCircle() {
		final Point center = new Point(scanner.nextInt(), scanner.nextInt());
		final int radius = scanner.nextInt();
		
		final Circle newCircle= new Circle(center, radius);
		comparableList.add(newCircle);
		return newCircle;
	}
	
	private static Line createLine() {
		final Point x = new Point(scanner.nextInt(), scanner.nextInt());
		final Point y = new Point(scanner.nextInt(), scanner.nextInt());
		
		final Line newLine = new Line(x, y);
		comparableList.add(newLine);
		return newLine;
	}
	
	private static void sortList(final List<MyComparable> list, final SortKind sortKind) {
		if (sortKind == SortKind.ASCENDING ) {
			for (int i = 0; i < list.size(); ++i)
				for (int j = i + 1; j  < list.size(); ++j) 
					if (list.get(i).compareTo(list.get(j)) == 1){
						MyComparable temp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, temp);
					}
		}
		else {
			for (int i = 0; i < list.size(); ++i)
				for (int j = i + 1; j  < list.size(); ++j) 
					if (list.get(i).compareTo(list.get(j)) == -1){
						MyComparable temp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, temp);
					}
		}
	}
}
