package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Class;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.ClassService;

@RestController
public class ClassController {

	@Autowired
	ClassService classService;
	
	@GetMapping(path = "/getChildClass")
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<?> getChildClass()
	{
		Class childClass = classService.getChildClass();
		if(childClass == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to get child class", childClass));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Child class's information", childClass));
		}
	}
}
