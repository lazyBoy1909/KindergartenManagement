package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseObject;
import com.example.demo.service.AccountService;

@RestController
public class NotificationController {

	@Autowired
	AccountService accountService;
	@GetMapping(path="/getRole")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT','ROLE_TEACHER')")
	public ResponseEntity<?> getRoleOfUser()
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Role of user", accountService.getRole()));
	}
}
