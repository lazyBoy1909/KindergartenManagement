package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

public class Student_Parent {
	private UUID studentID;
	private UUID classID;
	private Date dateOfBirth;
	private String studentName;
	private int studentGender;
	private UUID parentID;
	private String parentName;
	private Date parentDob;
	private String parentAddress;
	private String parentEmail;
	private int parentGender;
	public UUID getStudentID() {
		return studentID;
	}
	public void setStudentID(UUID studentID) {
		this.studentID = studentID;
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
	public int getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(int studentGender) {
		this.studentGender = studentGender;
	}
	public UUID getParentID() {
		return parentID;
	}
	public void setParentID(UUID parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Date getParentDob() {
		return parentDob;
	}
	public void setParentDob(Date parentDob) {
		this.parentDob = parentDob;
	}
	public String getParentAddress() {
		return parentAddress;
	}
	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public int getParentGender() {
		return parentGender;
	}
	public void setParentGender(int parentGender) {
		this.parentGender = parentGender;
	}
	public Student_Parent() {
		super();
	}
	public Student_Parent(UUID studentID, UUID classID, Date dateOfBirth, String studentName, int studentGender,
			UUID parentID, String parentName, Date parentDob, String parentAddress, String parentEmail,
			int parentGender) {
		super();
		this.studentID = studentID;
		this.classID = classID;
		this.dateOfBirth = dateOfBirth;
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.parentID = parentID;
		this.parentName = parentName;
		this.parentDob = parentDob;
		this.parentAddress = parentAddress;
		this.parentEmail = parentEmail;
		this.parentGender = parentGender;
	}
	
	
}
