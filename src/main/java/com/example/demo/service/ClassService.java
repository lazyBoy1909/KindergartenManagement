package com.example.demo.service;

import java.util.List;
import java.util.UUID;


import com.example.demo.model.Class;

public interface ClassService {
	public List<Class> getAllClasses();
	public Boolean addNewClass(Class newClass);
	public Boolean deleteClass(UUID classID);
	public Boolean updateClass(Class updateClass);
	public Class getChildClass();
	public Class getClassByID(UUID classID);
}
