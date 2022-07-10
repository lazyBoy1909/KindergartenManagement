package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Activity;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.ActivityService;

@RestController
public class ActivityController {
	@Autowired
	ActivityService activityService;
	
	@PostMapping(path = "/addNewActivity")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addNewActivity(@RequestBody Activity newActivity)
	{
		if(activityService.addNewActivity(newActivity))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add new activity successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@DeleteMapping(path = "/deleteActivity")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteActivity(@RequestParam UUID activityID)
	{
		if(activityService.deleteActivity(activityID))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "delete activity successfully", true));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", false));
		}
	}
	
	@GetMapping(path = "/getActivities")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_PARENT')")
	public ResponseEntity<?> getActivity()
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Activities", activityService.getActivity()));
	}
	
	@GetMapping(path = "/getActivity")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_PARENT')")
	public ResponseEntity<?> getActivityByID(@RequestParam("activityID") UUID activityID)
	{
		Activity activity = activityService.getActivityByID(activityID);
		if(activity != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Activitiy's information", activity));

		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input", activity));

		}
	}
}
