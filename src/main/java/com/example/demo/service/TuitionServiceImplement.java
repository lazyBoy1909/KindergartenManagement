package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
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

	@Override
	public Boolean addTuition(List<Tuition> listTuition) {
		for(int i=0;i<listTuition.size();i++) {
			if(listTuition.get(i).getMonth() < 1 || listTuition.get(i).getMonth() > 12) return false;
			try {
				Student student = studentRepository.findById(listTuition.get(i).getStudentID()).get();
				tuitionRepository.save(listTuition.get(i));
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean updateTuition(Tuition tuition) {
		if(tuition.getMonth() < 1 || tuition.getMonth() > 12) return false;
		try {
			Student student = studentRepository.findById(tuition.getStudentID()).get();
			Tuition foundTuition = tuitionRepository.findById(tuition.getTuitionID()).get();
			tuitionRepository.save(tuition);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
