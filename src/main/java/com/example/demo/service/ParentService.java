package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Parent;

public interface ParentService {
	public List<Parent> getAllParents();
	public List<Parent> getParentByClassID(UUID classID);
	public Parent getParentByID(UUID parentID);
}
