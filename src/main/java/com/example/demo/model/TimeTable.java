package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "time_table")
public class TimeTable {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "time_table_id")
	private UUID timeTableID;
	@Column(name = "day_of_the_week")
	private int dayOfTheWeek;
	@Column(name = "class_id")
	private UUID classID;
	@Column(name = "subject")
	private String subject;
	public UUID getTimeTableID() {
		return timeTableID;
	}
	public void setTimeTableID(UUID timeTableID) {
		this.timeTableID = timeTableID;
	}
	public int getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	public void setDayOfTheWeek(int dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
	public UUID getClassID() {
		return classID;
	}
	public void setClassID(UUID classID) {
		this.classID = classID;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TimeTable(UUID timeTableID, int dayOfTheWeek, UUID classID, String subject) {
		super();
		this.timeTableID = timeTableID;
		this.dayOfTheWeek = dayOfTheWeek;
		this.classID = classID;
		this.subject = subject;
	}
	public TimeTable() {
		super();
	}
	
}
