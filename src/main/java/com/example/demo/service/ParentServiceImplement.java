package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Parent;
import com.example.demo.model.Student;
import com.example.demo.repository.ParentRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class ParentServiceImplement implements ParentService {

	@Autowired
	ParentRepository parentRepository;
	
	@Autowired
	StudentRepository studentRepository;
	@Override
	public List<Parent> getAllParents() {
		return parentRepository.findAll();
	}
	@Override
	public List<Parent> getParentByClassID(UUID classID) {
		List<Parent> listParent = new ArrayList<Parent>();
		List<Student> listStudent = studentRepository.findStudentByClassID(classID);
		for(int i=0;i<listStudent.size();i++)
		{
			 Parent  parent;
			 try 
			 {
				 parent = parentRepository.findById(listStudent.get(i).getParentID()).get();
			 }
			 catch(NoSuchElementException e)
			 {
				 e.printStackTrace();
				 continue;
			 }
			 listParent.add(parent);
		}
		return listParent;
	}
	@Override
	public Parent getParentByID(UUID parentID) {
		try
		{
			Parent parent = parentRepository.findById(parentID).get();
			return parent;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void addNewParent(Parent parent) {
		parentRepository.save(parent);
	}

}
