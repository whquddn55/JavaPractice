package Practice5;

public class Student {
	private final String name;
	private School school;
	private int grade;
	
	public Student(final School school, final String name, final int grade) {
		this.school = school;
		this.name = name;
		this.grade = grade;
	}
	
	public boolean equals(final String name, final int grade) {
		if (name.equals(this.name) && grade == this.grade)
			return true;
		else
			return false;
	}
	
	public String toString() {
		String msg = "[Name : " + name + ", School : " + school.getName() + ", " + grade + "ÇÐ³â]";
		return msg;
	}
}
