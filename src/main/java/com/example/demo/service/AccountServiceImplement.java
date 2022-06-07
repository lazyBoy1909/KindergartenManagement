package com.example.demo.service;

import java.awt.print.Printable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement implements AccountService {

	@Override
	public String getRole() {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(userDetails);
    	return userDetails.getAuthorities().toString();
	}
}