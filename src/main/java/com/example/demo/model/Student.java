package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "student")
public class Student {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "student_id")
	private UUID studentID;
	@Column(name = "parent_id")
	private UUID parentID;
	@Column(name = "class_id")
	private UUID classID;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "student_name")
	private String studentName;
	public UUID getStudentID() {
		return studentID;
	}
	public void setStudentID(UUID studentID) {
		this.studentID = studentID;
	}
	public UUID getParentID() {
		return parentID;
	}
	public void setParentID(UUID parentID) {
		this.parentID = parentID;
	}
	public UUID getClassID() {
		return classID;
	}
	public void setClassID(UUID classID) {
		this.classID = classID;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Student(UUID studentID, UUID parentID, UUID classID, Date dateOfBirth, String studentName) {
		super();
		this.studentID = studentID;
		this.parentID = parentID;
		this.classID = classID;
		this.dateOfBirth = dateOfBirth;
		this.studentName = studentName;
	}
	public Student() {
		super();
	}
	
}
