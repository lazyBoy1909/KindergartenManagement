package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Activity;
import com.example.demo.model.Class;
import com.example.demo.model.ResponseObject;
import com.example.demo.model.Teacher;
import com.example.demo.model.Tuition;
import com.example.demo.service.ActivityService;
import com.example.demo.service.ClassService;
import com.example.demo.service.ParentService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.TuitionService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	ActivityService activityService;
	@Autowired
	ClassService classService;
	@Autowired
	ParentService parentService;
	@Autowired
	TeacherService teacherService;
	@Autowired 
	TuitionService tuitionService;
	
	class NumberOfStudent {
		int numberOfStudent;

		public NumberOfStudent(int numberOfStudent) {
			super();
			this.numberOfStudent = numberOfStudent;
		}

		public int getNumberOfStudent() {
			return numberOfStudent;
		}

		public void setNumberOfStudent(int numberOfStudent) {
			this.numberOfStudent = numberOfStudent;
		}
		
	}
	
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
	
	@GetMapping(path = "/getAllTeachers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTeachers()
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "All Teachers", teacherService.getAllTeacher()));
	}
	
	@PostMapping(path = "/addNewTeacher")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addNewTeacher(@RequestBody Teacher newTeacher)
	{
		if(teacherService.addNewTeacher(newTeacher))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add new teacher successfully", true));
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to add new teacher", false));
	}
	
	@PutMapping(path = "/updateTeacher")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTeacher(@RequestParam("teacherID") UUID teacherID, @RequestBody Teacher teacher)
	{
		if(teacherService.updateTeacher(teacherID, teacher))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "update teacher successfully", true));
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to update teacher", false));
	}
	
	@DeleteMapping(path = "/deleteTeacher")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteTeacher(@RequestParam("teacherID") UUID teacherID)
	{
		if(teacherService.deleteTeacher(teacherID))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "delete teacher successfully", true));

		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Failed to delete teacher", false));

		}
	}
	
	@GetMapping(path = "/studentNumber")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> numberStudent(@RequestParam("teacherID") UUID teacherID)
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "number of students", teacherService.numberOfStudent(teacherID)));
	}
	
	@GetMapping(path = "/getTeacher")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTeacher(@RequestParam("teacherID") UUID teacherID)
	{
		Teacher teacher = teacherService.getTeacher(teacherID);
		int numberOfStudent = teacherService.numberOfStudent(teacherID);
		List<Object> result = new ArrayList<Object>();
		result.add(teacher);
		result.add(new NumberOfStudent(numberOfStudent));
		if(teacher != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Teacher's information", result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "No teacher", result));
		}
	}
	
	@PostMapping(path = "/addTuition")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTuition(@RequestBody List<Tuition> listTuition)
	{
		if(tuitionService.addTuition(listTuition))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add tuitions successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@PutMapping(path = "/updateTuition")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTuition(@RequestBody Tuition tuition) {
		if(tuitionService.updateTuition(tuition))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Update tuition successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
}
