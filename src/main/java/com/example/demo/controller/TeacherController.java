package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseObject;
import com.example.demo.model.Teacher;
import com.example.demo.model.TimeTable;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import com.example.demo.service.TimeTableService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TimeTableService timeTableService;
    @GetMapping(path ="/teacherInfor")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> getTeacherInfor()
    {
    	Teacher teacher = teacherService.getTeacherInforByUsername();
    	if(teacher == null)
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "No teacher found", null));
    	}
    	return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseObject("Successful", "Teacher Information", teacher));
    }
    
    @GetMapping(path = "/timeTableInfor")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> getTimeTable(@RequestParam("classID") UUID classID)
    {
    	List<TimeTable>  lsitOfTimeTable = timeTableService.getTimetableByClassID(classID);
    	if(lsitOfTimeTable.size() == 0)
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "No time table found", null));
    	}
    	return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseObject("Successful", "Time table Information", lsitOfTimeTable));
    }
    
    @PostMapping(path = "/addTimeTable")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> addTimeTable(@RequestBody TimeTable newTimeTable)
    {
    	if(timeTableService.addNewTimeTable(newTimeTable))
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add new time table successful", true));
    	}
    	else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Add new time table failed", false));
    }
    
    @PutMapping(path = "/changeTimeTable")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> changeTimeTable(@RequestBody TimeTable timeTable)
    {
    	if(timeTableService.changeTimeTable(timeTable)) 
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Change time table successful ", true));
    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid Input", false));
    	}
    }
    
    @DeleteMapping(path ="/deleteTimeTable")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> deleteTimeTable(@RequestParam("timeTableID") UUID timeTableID)
    {
    	if(timeTableService.deleteTimeTable(timeTableID))
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Delete time table successful ", true));
    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid Input", false));
    	}
    }
}
