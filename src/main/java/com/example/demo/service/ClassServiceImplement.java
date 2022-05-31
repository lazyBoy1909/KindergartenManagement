package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class ClassServiceImplement implements ClassService {

	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Class> getAllClasses() {
		return classRepository.findAll();
	}

	@Override
	public Boolean addNewClass(Class newClass) {
		Teacher teacher;
		try
		{
			teacher = teacherRepository.findById(newClass.getTeacherID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false; 
		}
		classRepository.save(newClass);
		return true;
		
	}

	@Override
	public Boolean deleteClass(UUID classID) {
		Class deleteClass;
		try
		{
			deleteClass = classRepository.findById(classID).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		classRepository.delete(deleteClass);
		return true;
	}

	@Override
	public Boolean updateClass(Class updateClass) {
		Class classNeedUpdate;
		try
		{
			classNeedUpdate = classRepository.findById(updateClass.getClassID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		classRepository.save(updateClass);
		return true;
	}

	@Override
	public Class getChildClass() {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String username = userDetails.getName();
    	UUID parentID = accountRepository.getAccountByUsername(username).getUserID();
    	System.out.println(parentID);
    	Student student =  studentRepository.findStudentByParentID(parentID).get(0);
    	System.out.println(student.getStudentID());
    	return classRepository.getClassByStudentID(student.getStudentID());
	}

}
