package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherServiceImplement;


@SpringBootTest
public class TeacherServiceTest {
    @Mock
	TeacherRepository teacherRepository;

	@InjectMocks
	TeacherServiceImplement teacherServiceImplement;
	
	@Test
	void testGetAllTeacher() {
		List<Teacher> mockTeachers = new ArrayList<>();
		for (int i=0; i< 5; i++) {
			mockTeachers.add(new Teacher(UUID.randomUUID(), "teacherName", new Date(), "subject", "teacherAddress", 1, "teacherEmail", "teacherPhoneNumber"));
		}

		when(teacherRepository.findAll()).thenReturn(mockTeachers);
        
		List<Teacher> actualClass = teacherServiceImplement.getAllTeacher();

		assertEquals(actualClass.size(), mockTeachers.size());

		verify(teacherRepository).findAll();
	}
}
