package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseObject;
import com.example.demo.model.Student;
import com.example.demo.model.Tuition;
import com.example.demo.service.StudentService;
import com.example.demo.service.TuitionService;

@RestController
@RequestMapping("/parent")
public class ParentController {
	
	@Autowired
	TuitionService tuitionService;
	@Autowired
	StudentService studentService;
	@GetMapping(path = "/getTuition")
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<?> getTuition()
	{
		Tuition tuition = tuitionService.getTuitionByStudentID();
		if(tuition == null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", tuition));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Tuition's Information", tuition));
		}
	}
	
	@GetMapping(path = "/getChildStudentInfor")
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<?> getChildStudentInfor()
	{
		Student student = studentService.getChildStudentInfor();
		if(student == null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", student));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Child's Information", student));
		}
	}
	
}
