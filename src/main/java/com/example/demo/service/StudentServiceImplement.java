package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.ParentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.TuitionRepository;
import com.example.demo.model.Class;
import com.example.demo.model.Parent;

@Service
public class StudentServiceImplement implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TuitionRepository tuitionRepository;
	public List<Student> getAllStudentInfor() {
		List<Student> listStudents = studentRepository.findAll();
		return listStudents;
	}
	@Override
	public Boolean changeStudentInfor(Student student) {
		Student changeStudent = null;
		Class changeClass = null;
		Parent changeParent = null;
		//studentID not existed
		try 
		{
			 changeStudent = studentRepository.findById(student.getStudentID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		
		// classID not existed
		try 
		{
			 changeClass = classRepository.findById(student.getClassID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		
		//parentID not existed
		try 
		{
			 changeParent = parentRepository.findById(student.getParentID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		changeStudent = student;
		studentRepository.save(changeStudent);
		return true;
	}
	@Override
	public Boolean addNewStudent(Student student) {
		Class changeClass = null;
		Parent changeParent = null;
		// classID not existed
		try 
		{
			 changeClass = classRepository.findById(student.getClassID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		
		//parentID not existed
		try 
		{
			 changeParent = parentRepository.findById(student.getParentID()).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		studentRepository.save(student);
		return true;
	}
	@Override
	public Student getStudentInfor(UUID studentID) {
		Student student = null;
		try 
		{
			 student = studentRepository.findById(studentID).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public void deleteStudent(UUID studentID) {
		tuitionRepository.delete(tuitionRepository.getTuitionByStudentID(studentID));
		studentRepository.deleteById(studentID);
	}
	@Override
	public Student getChildStudentInfor() {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String username = userDetails.getName();
    	UUID parentID = accountRepository.getAccountByUsername(username).getUserID();
    	return studentRepository.findStudentByParentID(parentID).get(0);
	}
	@Override
	public List<Student> getAllStudentsByClassID(UUID classID) {
		try {
			Class classFound = classRepository.findById(classID).get();
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
		return studentRepository.findStudentByClassID(classID);
	}

}
