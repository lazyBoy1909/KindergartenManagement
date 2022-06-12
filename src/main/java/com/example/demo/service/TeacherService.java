package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Teacher;
import com.example.demo.model.TimeTable;

public interface TeacherService {
	public Teacher getTeacherInforByUsername();
	public List<Teacher> getAllTeacher();
	public Boolean addNewTeacher(Teacher teacher);
	public Boolean deleteTeacher(UUID teacherID);
	public Boolean updateTeacher(UUID teacherID, Teacher teacher);
	public int numberOfStudent(UUID teacherID);
	public Teacher getTeacher(UUID teacherID);
}
