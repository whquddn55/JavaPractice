package Practice5;
import java.util.ArrayList;
import java.util.Scanner;

import Practice5.*;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static SchoolManager schoolManager = new SchoolManager();
	
	private enum OperationKind{ADD, FIND, CLEAR, LIST, INVALID};
	
	public static void main(String[] args) {
		while(true) {
			final OperationKind op = getOperation();
			switch(op) {
			case ADD: 
				final Student newStudent = createStudent();
				System.out.println(newStudent);
				break;
			
			case FIND:
				findStudent();
				break;
			case CLEAR:
				schoolManager.removeAllSchools();
				break;
			case LIST:
				System.out.println(schoolManager);
				break;
			}
		}
	}
	
	private static OperationKind getOperation() {
		System.out.print("Enter Operation String : ");
		return OperationKind.valueOf(scanner.next().toUpperCase());
	}
	
	private static Student createStudent() {
		final String schoolName = scanner.next();
		final String name = scanner.next();
		final int grade = scanner.nextInt();
		
		School theSchool = schoolManager.findSchool(schoolName);
		if (theSchool == null)
			theSchool = schoolManager.createSchool(schoolName);
		final Student newStudent = new Student(theSchool, name, grade);
		theSchool.addStudent(newStudent);
		
		return newStudent;
	}
	
	private static void findStudent() {
		final String toFindName = scanner.next();
		final int toFindGrade = scanner.nextInt();
		
		final ArrayList<Student> foundStudents = schoolManager.findStudents(toFindName, toFindGrade);
		
		if (foundStudents.size() > 0) {
			System.out.println(foundStudents.size() + " found!");
			for (Student stu : foundStudents)
				System.out.println(stu);
		}
		else {
			System.out.println("No Student Found with name " + toFindName + " and grade " + toFindGrade);
		}
	}
}
