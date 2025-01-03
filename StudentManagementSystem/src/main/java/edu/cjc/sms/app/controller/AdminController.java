package edu.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Controller
public class AdminController {
	@Autowired
	StudentServiceI ssi;
        
	  
	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String u,@RequestParam("password") String p,Model m) {
		if(u.equals("ADMIN")  && p.equals("ADMIN"))
		{
			m.addAttribute("data",ssi.getAllStudents());
			return "adminscreen";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/enroll_student")
	public String addStudent(@ModelAttribute Student s,Model m) {
		  List<Student> list=ssi.addStudent(s);
		  m.addAttribute("data", list);
		return "adminscreen";
	}
	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber,Model m) {
	 List<Student>result  =ssi.searchStudentByBatch(batchNumber);
	 if(result.size()>0)
	 {
		 m.addAttribute("data",result);
	 }else {
		 
        m.addAttribute("message", "NO recored are avilable for the batch  '"+batchNumber+"'....!");
	 }
		
		return "adminscreen";
	}
	@RequestMapping("fees")
	public String onfees(@RequestParam int id,Model m) {
		
		   Student st=      ssi.getSingleStudent(id);
		   
		   m.addAttribute("st", st);
		   
		   return "fees";
		   
	}
	  @RequestMapping("payfees")
	 public String payfees(@RequestParam int studentid,@RequestParam float ammount,Model m) {
		        ssi.updateStudentFees(studentid,ammount);
		         List<Student> students            =ssi.getAllStudents();
		         m.addAttribute("data", students);
		  return "adminscreen";
	  }
	  @RequestMapping("remove")
	  public String removeDelete(@RequestParam int id,Model m) {
		  
		  
		  ssi.removeStudent(id);
		  List<Student>list            =ssi.getAllStudents();
		  m.addAttribute("data", list);
		  
		  return "adminscreen";
	  }
	  
	  @RequestMapping("batch")
		public String batch(@RequestParam int id,Model m) {
			
			   Student st=      ssi.getSingleStudent(id);
			   
			   m.addAttribute("st", st);
			   
			   return "batch";
}
	  
	  @RequestMapping("shiftbatch")
		 public String shiftbatch(@RequestParam int studentid,@RequestParam String batch,@RequestParam String mode,Model m) {
			        ssi.shiftStudentBatch(studentid,batch,mode);
			         List<Student> students            =ssi.getAllStudents();
			         m.addAttribute("data", students);
			  return "adminscreen";
		  }
}
