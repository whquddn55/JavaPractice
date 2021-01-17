package Practice5;

import java.util.ArrayList;

public class SchoolManager {
	final private ArrayList<School> schools = new ArrayList<School>();
	
	public void removeAllSchools() {
		schools.clear();
	}
	
	public School createSchool(final String schoolName) {
		final School newSchool = new School(schoolName);
		schools.add(newSchool);
		return newSchool;
	}
	
	public School findSchool(final String schoolName) {
		School result = null;
		for (School school : schools) 
			if (school.equals(schoolName)) {
				result = school;
				break;
			}
		return result;
	}
	
	public ArrayList<Student> findStudents(final String name, final int grade) {
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		for (School school : schools) {
			Student foundStudent = school.findStudent(name, grade);
			if (foundStudent != null)
				foundStudents.add(foundStudent);
		}
		return foundStudents;
	}
	
	public String toString() {
		StringBuilder msg = new StringBuilder("Total School Count : ");
		msg.append(schools.size());
		msg.append('\n');
		for (School school : schools) {
			msg.append(school);
			msg.append('\n');
		}
		
		return msg.toString();
	}
}
