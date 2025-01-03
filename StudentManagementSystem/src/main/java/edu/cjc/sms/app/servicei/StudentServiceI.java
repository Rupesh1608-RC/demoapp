package edu.cjc.sms.app.servicei;

import java.util.List;

import edu.cjc.sms.app.model.Student;

public interface StudentServiceI {

	public List<Student> addStudent(Student s);
	public List<Student> getAllStudents();
	
	public List<Student> searchStudentByBatch(String batchNumber);
	public Student getSingleStudent(int id);
	public void updateStudentFees(int studentid, float ammount);
	public void removeStudent(int id);

	public void shiftStudentBatch(int studentid, String batch, String mode);

}
