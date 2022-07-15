package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Email;
import com.example.demo.model.Parent;
import com.example.demo.model.ResponseObject;
import com.example.demo.model.Student;
import com.example.demo.model.Tuition;
import com.example.demo.service.EmailService;
import com.example.demo.service.ParentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TuitionService;

@RestController
@RequestMapping("/parent")
public class ParentController {
	
	@Autowired
	EmailService emailService;
	@Autowired
	TuitionService tuitionService;
	@Autowired
	StudentService studentService;
	@Autowired
	ParentService parentService;
	@GetMapping(path = "/getTuition")
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<?> getTuition()
	{
		List<Tuition> tuition = tuitionService.getTuitionByStudentID();
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
	
	@PostMapping(path = "/emails")
	@PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<?> sendEmail(@RequestBody Email email)
    {
    	if(emailService.sendEmailForParent(email))
    	{
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Send email successfully ", true));
    	}
    	else
    	{
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "List Parent in class ", false));

    	}
    }
	
	@GetMapping(path = "/getParent")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
	public ResponseEntity<?> getParentByID(@RequestParam("parentID") UUID parentID)
	{
		Parent parent = parentService.getParentByID(parentID);
		if(parent != null)
		{
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "parent's information ", parent));

		}
		else
		{
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input ", parent));

		}
	}
	
	@PutMapping(path = "updateParent")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
	public ResponseEntity<?> updateParent(@RequestBody Parent parent) {
		if(parentService.updateParent(parent)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Update parent's information successfully ", true));
		} else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input ", false));
		}
	}
}
