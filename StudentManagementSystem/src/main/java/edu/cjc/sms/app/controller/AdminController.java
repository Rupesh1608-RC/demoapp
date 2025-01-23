package edu.cjc.sms.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String onLogin(@RequestParam("username") String u, @RequestParam("password") String p, Model m) {
		if (u.equals("ADMIN") && p.equals("ADMIN")) {
			m.addAttribute("data", ssi.getAllStudents());
			return "adminscreen";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/enroll_student", method = RequestMethod.POST)
	public String saveStudent(@RequestParam("studentFullName") String studentFullName,
			@RequestParam("studentEmail") String studentEmail, @RequestParam("studentAge") byte studentAge,
			@RequestParam("studentCollageName") String studentCollageName, @RequestParam("feesPaid") float feesPaid,
			@RequestParam("studentCourse") String studentCourse, @RequestParam("batchMode") String batchMode,
			@RequestParam("batchNumber") String batchNumber, @RequestParam("photo") MultipartFile photo, Model m)
			throws IOException {

		Student s = new Student();
		s.setStudentFullName(studentFullName);
		s.setStudentEmail(studentEmail);
		s.setStudentCourse(studentCourse);
		s.setStudentCollageName(studentCollageName);
		s.setStudentAge(studentAge);
		s.setFeesPaid(feesPaid);
		s.setBatchNumber(batchNumber);
		s.setBatchMode(batchMode);

		String image = Base64.getEncoder().encodeToString(photo.getBytes());

		s.setPhoto(image);

		ssi.saveStudent(s);
		List<Student> list = ssi.getAllStudents();
		m.addAttribute("data", list);

		return "adminscreen";
	}

	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber, Model m) {
		List<Student> result = ssi.searchStudentByBatch(batchNumber);
		if (result.size() > 0) {
			m.addAttribute("data", result);
		} else {

			List<Student> students = ssi.getAllStudents();
			m.addAttribute("data", students);
			m.addAttribute("message", "NO recored are avilable for the batch  '" + batchNumber + "'....!");
		}

		return "adminscreen";
	}

	@RequestMapping("fees")
	public String onfees(@RequestParam int id, Model m) {

		Student st = ssi.getSingleStudent(id);

		m.addAttribute("st", st);

		return "fees";

	}

	@RequestMapping("payfees")
	public String payfees(@RequestParam int studentid, @RequestParam float ammount, Model m) {
		ssi.updateStudentFees(studentid, ammount);
		List<Student> students = ssi.getAllStudents();
		m.addAttribute("data", students);
		return "adminscreen";
	}

	@RequestMapping("remove")
	public String removeDelete(@RequestParam int id, Model m) {

		ssi.removeStudent(id);
		List<Student> list = ssi.getAllStudents();
		m.addAttribute("data", list);

		return "adminscreen";
	}

	@RequestMapping("batch")
	public String batch(@RequestParam int id, Model m) {

		Student st = ssi.getSingleStudent(id);

		m.addAttribute("st", st);

		return "batch";
	}

	@RequestMapping("shiftbatch")
	public String shiftbatch(@RequestParam int studentid, @RequestParam String batch, @RequestParam String mode,
			Model m) {
		ssi.shiftStudentBatch(studentid, batch, mode);
		List<Student> students = ssi.getAllStudents();
		m.addAttribute("data", students);
		return "adminscreen";
	}
}
