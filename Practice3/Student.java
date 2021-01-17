package Practice3;
public class Student {
	private String name;
	private int grade;
	
	public Student(String name, int grade) {
		this.name = name;
		this.grade = grade;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Student))
			return false;
		Student otherStudent = (Student)other;
		if (otherStudent.name.equals(name) && otherStudent.grade == grade)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "[" + name + ", " + grade + "]";
	}
}
