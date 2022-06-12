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
    	Student student =  studentRepository.findStudentByParentID(parentID).get(0);
    	return classRepository.getClassByStudentID(student.getStudentID());
	}

	@Override
	public Class getClassByID(UUID classID) {
		try
		{
			Class foundClass = classRepository.findById(classID).get();
			return foundClass;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Class getClassByTeacherID(UUID teacherID) {
		List<Class> listClasses = classRepository.getClassIDByTeacherID(teacherID);
		if(listClasses.size() == 0) return null;
		else return classRepository.getClassIDByTeacherID(teacherID).get(0);
	}

}
