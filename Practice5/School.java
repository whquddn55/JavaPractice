package Practice5;

import java.util.ArrayList;

public class School {
	private final String name;
	private final ArrayList<Student> students = new ArrayList<Student>();
	
	public School(final String name) {
		this.name = name;
	}
	
	public void addStudent(final Student newStudent) {
		students.add(newStudent);
	}
	
	public Student findStudent(final String name, final int grade) {
		for (Student student : students) {
			if (student.equals(name, grade))
				return student;
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof School))
			return false;
		return ((School)other).name.equals(this.name);
	}
	public boolean equals(String name) {
		return name.equals(this.name);
	}
	
	public String toString() {
		StringBuilder msg = new StringBuilder("SchoolName : ");
		msg.append(name);
		msg.append(", Student Count : ");
		msg.append(students.size());
		msg.append('\n');
		for (Student student : students){
			msg.append(student);
			msg.append('\n');
		}
		
		return msg.toString();
	}
}
