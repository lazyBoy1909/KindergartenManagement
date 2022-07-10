package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.model.Class;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherServiceImplement implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	StudentRepository studentRepository;
	
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
	public Boolean updateTeacher(UUID teacherID, Teacher updateTeacher) {
		try
		{
			Teacher oldVersion = teacherRepository.findById(teacherID).get();
//			oldVersion.setTeacherAddress(updateTeacher.getTeacherAddress());
//			oldVersion.setTeacherEmail(updateTeacher.getTeacherEmail());
//			oldVersion.setTeacherDob(updateTeacher.getTeacherDob());
//			oldVersion.setTeacherGender(updateTeacher.getTeacherGender());
//			oldVersion.setTeacherName(updateTeacher.getTeacherName());
//			oldVersion.setSubject(updateTeacher.getSubject());
//			oldVersion.setTeacherPhoneNumber(updateTeacher.getTeacherPhoneNumber());
			teacherRepository.save(updateTeacher);
			return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public int numberOfStudent(UUID teacherID) {
		List<Class> listClasses = classRepository.getClassIDByTeacherID(teacherID);
		if(listClasses.size() == 0) return 0;
		Class foundClass = listClasses.get(0);
		System.out.println(foundClass.getClassName());
		List<Student> listStudents = studentRepository.findStudentByClassID(foundClass.getClassID());
		return listStudents.size();
	}
	@Override
	public Teacher getTeacher(UUID teacherID) {
		try {
			Teacher teacher = teacherRepository.findById(teacherID).get();
			return teacher;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
