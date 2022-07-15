package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TimeTable;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.TimeTableRepository;

@Service
public class TimeTableServiceImplement implements TimeTableService {
	
	
	@Autowired
	TimeTableRepository timeTableRepository;
	@Autowired
	ClassRepository classRepository;
	@Override
	public List<TimeTable> getTimetableByClassID(UUID classID) {
		return timeTableRepository.getTimeTableByClassID(classID);
	}

	@Override
	public Boolean addNewTimeTable(TimeTable newTimeTable) {
		if(newTimeTable.getDayOfTheWeek() < 2 || newTimeTable.getDayOfTheWeek() > 8) return false;
		if(classRepository.findById(newTimeTable.getClassID()) == null) return false;
		timeTableRepository.save(newTimeTable);
		return true;
	}

	@Override
	public Boolean changeTimeTable(TimeTable timeTable) {
		if(timeTable.getDayOfTheWeek() < 2 || timeTable.getDayOfTheWeek() > 8) 
		{
			return false;
		}
		TimeTable changedTimeTable;
		try 
		{
			changedTimeTable = timeTableRepository.findById(timeTable.getTimeTableID()).get();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("failed to load time table");
			e.printStackTrace();
			return false;
		}
		if(!timeTable.getClassID().equals(changedTimeTable.getClassID())) return false;
		changedTimeTable = timeTable;
		timeTableRepository.save(changedTimeTable);
		return true;
	}

	@Override
	public Boolean deleteTimeTable(UUID timeTableID) {
		TimeTable deleteTimeTable;
		try 
		{
			deleteTimeTable = timeTableRepository.findById(timeTableID).get();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("failed to load time table");
			e.printStackTrace();
			return false;
		}
		timeTableRepository.delete(deleteTimeTable);
		return true;
	}

	@Override
	public List<TimeTable> getAllTimeTable() {
		return timeTableRepository.findAll();
	}
}
