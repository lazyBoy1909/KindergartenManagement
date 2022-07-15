package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.TimeTable;

public interface TimeTableService {
	public List<TimeTable> getTimetableByClassID(UUID classID);
	public Boolean addNewTimeTable(TimeTable newTimeTable);
	public Boolean changeTimeTable(TimeTable timeTable);
	public Boolean deleteTimeTable(UUID timeTableID);
	public List<TimeTable> getAllTimeTable();
}
