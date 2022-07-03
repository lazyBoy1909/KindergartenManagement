package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Student;

public interface StudentService {
	public List<Student> getAllStudentInfor();
	public Boolean changeStudentInfor(Student student);
	public Boolean addNewStudent(Student student);
	public Student getStudentInfor(UUID studentID);
	public void deleteStudent(UUID studentID);
	public Student getChildStudentInfor();
	public List<Student> getAllStudentsByClassID(UUID classID);
}
