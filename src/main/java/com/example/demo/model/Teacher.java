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
@Table(name = "teacher")
public class Teacher {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "teacher_ID")
	private UUID teacherID;
	@Column(name = "teacher_name")
	private String teacherName;
	@Column(name = "teacher_dob")
	private Date teacherDob;
	@Column(name = "subject")
	private String subject;
	@Column(name = "teacher_address")
	private String teacherAddress;
	@Column(name = "teacher_gender")
	private int teacherGender;
	@Column(name = "teacher_email")
	private String teacherEmail;
	@Column(name = "teacher_phone_number")
	private String teacherPhoneNumber;
	public Teacher() {
		super();
	}
	public UUID getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(UUID teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Date getTeacherDob() {
		return teacherDob;
	}
	public void setTeacherDob(Date teacherDob) {
		this.teacherDob = teacherDob;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTeacherAddress() {
		return teacherAddress;
	}
	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}
	public int getTeacherGender() {
		return teacherGender;
	}
	public void setTeacherGender(int teacherGender) {
		this.teacherGender = teacherGender;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getTeacherPhoneNumber() {
		return teacherPhoneNumber;
	}
	public void setTeacherPhoneNumber(String teacherPhoneNumber) {
		this.teacherPhoneNumber = teacherPhoneNumber;
	}
	public Teacher(UUID teacherID, String teacherName, Date teacherDob, String subject, String teacherAddress,
			int teacherGender, String teacherEmail, String teacherPhoneNumber) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.teacherDob = teacherDob;
		this.subject = subject;
		this.teacherAddress = teacherAddress;
		this.teacherGender = teacherGender;
		this.teacherEmail = teacherEmail;
		this.teacherPhoneNumber = teacherPhoneNumber;
	}
	

	
}
