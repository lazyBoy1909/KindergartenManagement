package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
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
	@Override
	public List<Teacher> getAllTeacher() {
		return teacherRepository.findAll();
	}
	@Override
	public Boolean addNewTeacher(Teacher teacher) {
		if(teacher ==  null) return false;
		teacherRepository.save(teacher);
		return true;
	}
	@Override
	public Boolean deleteTeacher(UUID teacherID) {
		try
		{
			Teacher teacher = teacherRepository.findById(teacherID).get();
			teacherRepository.delete(teacher);
			return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean updateTeacher(UUID teacherID, Teacher teacher) {
		try
		{
			Teacher updateTeacher = teacherRepository.findById(teacherID).get();
			teacherRepository.save(teacher);
			return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
