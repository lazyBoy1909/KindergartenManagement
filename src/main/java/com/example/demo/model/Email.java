package com.example.demo.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

public class Email {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "email_id")
	private UUID emailID;
	@Column(name = "sender_email")
	private String senderEmail;
	@Column(name = "receiver_email")
	private String receiverEmail;
	@Column(name = "message")
	private String message;
	public UUID getEmailID() {
		return emailID;
	}
	public void setEmailID(UUID emailID) {
		this.emailID = emailID;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Email(UUID emailID, String senderEmail, String receiverEmail, String message) {
		super();
		this.emailID = emailID;
		this.senderEmail = senderEmail;
		this.receiverEmail = receiverEmail;
		this.message = message;
	}
	public Email() {
		super();
	}
	
	
}
