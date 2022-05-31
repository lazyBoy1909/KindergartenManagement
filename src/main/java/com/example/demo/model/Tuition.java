package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tuition")
public class Tuition {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "tuition_id")
	private UUID tuitionID;
	@Column(name = "month")
	private int month;
	@Column(name = "tuition_fee")
	private Double tuitionFee;
	@Column(name = "student_id")
	private UUID studentID;
	public UUID getTuitionID() {
		return tuitionID;
	}
	public void setTuitionID(UUID tuitionID) {
		this.tuitionID = tuitionID;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Double getTuitionFee() {
		return tuitionFee;
	}
	public void setTuitionFee(Double tuitionFee) {
		this.tuitionFee = tuitionFee;
	}
	public UUID getStudentID() {
		return studentID;
	}
	public void setStudentID(UUID studentID) {
		this.studentID = studentID;
	}
	public Tuition(UUID tuitionID, int month, Double tuitionFee, UUID studentID) {
		super();
		this.tuitionID = tuitionID;
		this.month = month;
		this.tuitionFee = tuitionFee;
		this.studentID = studentID;
	}
	public Tuition() {
		super();
	}
	
	
}
