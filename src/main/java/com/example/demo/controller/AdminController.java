package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Activity;
import com.example.demo.model.Class;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.ActivityService;
import com.example.demo.service.ClassService;
import com.example.demo.service.ParentService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	ActivityService activityService;
	@Autowired
	ClassService classService;
	@Autowired
	ParentService parentService;
	
	
	@PostMapping(path = "/addNewClass")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addNewClass(@RequestBody Class newClass)
	{
		if(classService.addNewClass(newClass))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add new class successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@DeleteMapping(path = "/deleteClass")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteClass(@RequestParam("classID") UUID classID)
	{
		if(classService.deleteClass(classID))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Delete class successfully", true));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@PutMapping(path = "/updateClass")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateClass(@RequestBody Class updateClass)
	{
		if(classService.updateClass(updateClass))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Update class successfully", true));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@GetMapping(path = "/getAllParents")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllParents()
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "All parents' information", parentService.getAllParents()));

	}
	
	@GetMapping(path = "/getClasses")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllClasses()
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successfull", "All Classes", classService.getAllClasses()));
	}
}
