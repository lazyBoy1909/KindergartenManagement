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
@Table(name = "activity")
public class Activity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "activity_id")
	private UUID activityID;
	@Column(name = "activity_name")
	private String activityName;
	@Column(name = "activity_time")
	private Date activityTime;
	@Column(name = "activity_content")
	private String activityContent;
	@Column(name = "activity_note")
	private String activityNote;
	public UUID getActivityID() {
		return activityID;
	}
	public void setActivityID(UUID activityID) {
		this.activityID = activityID;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public String getActivityNote() {
		return activityNote;
	}
	public void setActivityNote(String activityNote) {
		this.activityNote = activityNote;
	}
	public Activity(UUID activityID, String activityName, Date activityTime, String activityContent, String activityNote) {
		super();
		this.activityID = activityID;
		this.activityName = activityName;
		this.activityTime = activityTime;
		this.activityContent = activityContent;
		this.activityNote = activityNote;
	}
	public Activity() {
		super();
	}
	
	
}
