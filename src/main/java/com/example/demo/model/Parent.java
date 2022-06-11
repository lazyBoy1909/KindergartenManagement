package com.example.demo.model;

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
	@Column(name = "parent_age")
	private int parentAge;
	@Column(name = "parent_address")
	private String parentAddress;
	@Column(name = "parent_email")
	private String parentEmail;
	
	
	
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
	public int getParentAge() {
		return parentAge;
	}
	public void setParentAge(int parentAge) {
		this.parentAge = parentAge;
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
	public Parent(UUID parentID, String parentName, int parentAge, String parentAddress, String parentEmail) {
		super();
		this.parentID = parentID;
		this.parentName = parentName;
		this.parentAge = parentAge;
		this.parentAddress = parentAddress;
		this.parentEmail = parentEmail;
	}
	public Parent() {
		super();
	}
	
}
