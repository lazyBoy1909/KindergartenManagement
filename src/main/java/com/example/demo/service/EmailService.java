package com.example.demo.service;

import com.example.demo.model.Email;

public interface EmailService {

	public Boolean sendEmail(Email email);
	public Boolean sendEmailForParent(String email);
}
