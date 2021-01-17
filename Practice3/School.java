package Practice3;

public class School {
	private String name;
	private int sizeOfStudents;
	private Student[] students;
	private int lastIndexOfStudents;
	
	public School(String name, int sizeOfStudents) {
		this.name = name;
		this.sizeOfStudents = sizeOfStudents;
		students = new Student[sizeOfStudents];
	}
	
	public void addStudent(Student newStudent) {
		students[lastIndexOfStudents] = newStudent;
		lastIndexOfStudents++;
	}
	
	public Student findStudent(Student student) {
		for (int i = 0; i < lastIndexOfStudents; ++i)
			if (students[i].equals(student)) 
				return students[i];
			
		return null;
	}
	
	public void removeAllStudent() {
		lastIndexOfStudents = 0;
	}
	
	public String toString() {
		StringBuilder msg = new StringBuilder("School Name : ");
		msg.append(name);
		msg.append(", Student Count : ");
		msg.append(lastIndexOfStudents);
		msg.append("\n");
		for (int i = 0; i < lastIndexOfStudents; ++i)
			msg.append(students[i] + "\n");
		return msg.toString();
	}
}
