package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.ApplicationUser;
import com.example.demo.model.Parent;
import com.example.demo.model.ResponseObject;
import com.example.demo.model.Student;
import com.example.demo.model.Student_Parent;
import com.example.demo.model.Teacher;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.ParentService;
import com.example.demo.service.StudentService;

import java.awt.print.Printable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	ParentService parentService;
		@GetMapping(path = "/allStudentInfor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ResponseEntity<?> getAllStudentInfor()
	{
		return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "All students' information", studentService.getAllStudentInfor()));
	} 
	
	@PutMapping(path = "/changeStudentInfor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ResponseEntity<?> changeStudentInfor(@RequestBody Student student)
	{
		if(studentService.changeStudentInfor(student))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Change student information successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to change student information", false));
		}
	}
	
	@PostMapping(path = "/addNewStudent")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ResponseEntity<?> addNewStudent(@RequestBody Student_Parent studentAndParent)
	{
		Parent parent = new Parent(studentAndParent.getParentName(), 
				studentAndParent.getParentDob(), studentAndParent.getParentAddress(), studentAndParent.getParentEmail(), 
				studentAndParent.getParentGender());
		parentService.addNewParent(parent);
		Student student = new Student(UUID.randomUUID(), parent.getParentID(), 
				studentAndParent.getClassID(), studentAndParent.getDateOfBirth(),studentAndParent.getStudentName(),
				studentAndParent.getStudentGender());
		if(studentService.addNewStudent(student))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add new student information successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to add new student information successfully", false));
		}
	}
	
	@GetMapping(path = "/getStudentInfor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ResponseEntity<?> getStudentInfor(@RequestParam("studentID") UUID studentID)
	{
		Student student;
		if((student = studentService.getStudentInfor(studentID)) != null)
		{
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Student's information", student));
		}
		else 
		{
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "No student",null));
		}
	} 
	
	@DeleteMapping(path ="/deleteStudent")
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ResponseEntity<?> deleteStudent(@RequestParam("studentID") UUID studentID)
	{
		studentService.deleteStudent(studentID);
		return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "delete student successfully", null));
	}
	
	@GetMapping(path = "/getAllStudentsInClass")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_PARENT')")
	public ResponseEntity<?> getAllStudentsByClassID(@RequestParam("classID") UUID classID) {
		List<Student> listStudent = studentService.getAllStudentsByClassID(classID);
		if(listStudent != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Students' information", listStudent));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", null));
		}
	}
}
