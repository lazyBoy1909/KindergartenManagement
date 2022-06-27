package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Tuition;

public interface TuitionService {
	public Tuition getTuitionByStudentID();
	public Boolean addTuition(List<Tuition> listTuition);
	public Boolean updateTuition(Tuition tuition);
}
