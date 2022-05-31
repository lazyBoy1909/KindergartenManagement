package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.model.Tuition;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TuitionRepository;

@Service
public class TuitionServiceImplement implements TuitionService {

	@Autowired
	TuitionRepository tuitionRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Tuition getTuitionByStudentID() {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String username = userDetails.getName();
    	UUID parentID = accountRepository.getAccountByUsername(username).getUserID();
    	Student student =  studentRepository.findStudentByParentID(parentID).get(0);
    	System.out.print(student.getStudentName());
		return tuitionRepository.getTuitionByStudentID(student.getStudentID());
	}

}
