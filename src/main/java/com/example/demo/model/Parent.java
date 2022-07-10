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
@Table(name = "parent")
public class Parent {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "parent_id")
	private UUID parentID;
	@Column(name = "parent_name")
	private String parentName;
	@Column(name = "parent_dob")
	private Date parentDob;
	@Column(name = "parent_address")
	private String parentAddress;
	@Column(name = "parent_email")
	private String parentEmail;
	@Column(name = "parent_gender")
	private int parentGender;
	
	

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



	public Parent(UUID parentID, String parentName, Date parentDob, String parentAddress, String parentEmail,
			int parentGender) {
		super();
		this.parentID = parentID;
		this.parentName = parentName;
		this.parentDob = parentDob;
		this.parentAddress = parentAddress;
		this.parentEmail = parentEmail;
		this.parentGender = parentGender;
	}



	public Parent() {
		super();
	}



	public Parent(String parentName, Date parentDob, String parentAddress, String parentEmail, int parentGender) {
		super();
		this.parentName = parentName;
		this.parentDob = parentDob;
		this.parentAddress = parentAddress;
		this.parentEmail = parentEmail;
		this.parentGender = parentGender;
	}
	
}
