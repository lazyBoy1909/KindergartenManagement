package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "class")
public class Class {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "class_id")
	private UUID classID;
	@Column(name = "class_name")
	private String className;
	@Column(name = "teacher_id")
	private UUID teacherID;
	public UUID getClassID() {
		return classID;
	}
	public void setClassID(UUID classID) {
		this.classID = classID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public UUID getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(UUID teacherID) {
		this.teacherID = teacherID;
	}
	public Class(UUID classID, String className, UUID teacherID) {
		super();
		this.classID = classID;
		this.className = className;
		this.teacherID = teacherID;
	}
	public Class() {
		super();
	}
	
	
}
