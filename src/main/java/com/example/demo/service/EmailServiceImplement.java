package com.example.demo.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Class;
import com.example.demo.model.Email;
import com.example.demo.model.Parent;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.ParentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class EmailServiceImplement implements EmailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	ClassRepository classRepository;
	@Override
	public Boolean sendEmail(Email email) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email.getReceiverEmail());
			mailMessage.setText(email.getMessage());
			mailMessage.setSubject(email.getSenderEmail());
			javaMailSender.send(mailMessage);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean sendEmailForParent(String email) {
    	Authentication userDetails = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String username = userDetails.getName();
    	UUID parentID = accountRepository.getAccountByUsername(username).getUserID();
    	Student student = studentRepository.findStudentByParentID(parentID).get(0);
    	Class findClass = classRepository.getClassByStudentID(student.getStudentID());
    	Teacher teacher = teacherRepository.getTeacherByID(findClass.getTeacherID());
    	System.out.println(teacher.getTeacherName());
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(teacher.getTeacherEmail());
			mailMessage.setText(email);
			mailMessage.setSubject(teacher.getTeacherEmail());
			javaMailSender.send(mailMessage);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
