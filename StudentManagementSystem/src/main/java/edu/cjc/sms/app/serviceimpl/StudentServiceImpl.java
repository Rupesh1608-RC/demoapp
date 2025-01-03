package edu.cjc.sms.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repositary.StudentRepo;
import edu.cjc.sms.app.servicei.StudentServiceI;
@Service
public class StudentServiceImpl implements StudentServiceI{

	@Autowired
	StudentRepo sr;
	
	@Override
	public List<Student> addStudent(Student s) {
		  sr.save(s);		
		return sr.findAll();
	}

	@Override
	public List<Student> getAllStudents() {
		
		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentByBatch(String batchNumber) {
		
   List<Student>batchStudents = sr.findAllByBatchNumber(batchNumber);
   
		return batchStudents;
	}

	@Override
	public Student getSingleStudent(int id) {
		     Optional<Student>op            =sr.findById(id);
		return op.get();
	}

	@Override
	public void updateStudentFees(int studentid, float ammount) {
		            
		     Optional<Student> opStudent       =sr.findById(studentid);
		                   Student st     =opStudent.get();
		                   
		                   st.setFeesPaid(st.getFeesPaid()+ammount);
		                   sr.save(st);
	}

	@Override
	public void removeStudent(int id) {

		sr.deleteById(id);
	}

	

	@Override
	public void shiftStudentBatch(int studentid, String batch, String mode) {
		
		  Optional<Student>opStudent     =sr.findById(studentid);
          Student st =opStudent.get();
           st.setBatchNumber(batch);
           st.setBatchMode(mode);
           sr.save(st);
		
	}
         

	
}
