package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teacher;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherServiceImplement implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	AccountRepository accountRepository;
	@Override
	public Teacher getTeacherInforByUsername() {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String username = userDetails.getName();
    	UUID teacherID = accountRepository.getAccountByUsername(username).getUserID();
    	Teacher teacher = teacherRepository.getTeacherByID(teacherID);
		return teacher;
	}

}
