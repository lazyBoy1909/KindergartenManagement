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
	private UUID activityContent;
	@Column(name = "activity_note")
	private UUID activityNote;
}
