package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;
import com.example.demo.service.ClassServiceImplement;

import ch.qos.logback.core.boolex.Matcher;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	ClassRepository classRepository;

	@InjectMocks
	ClassServiceImplement classServiceImplement;
	
	@Test
	void contextLoads() {
		List<Class> mockClass = new ArrayList<>();
		for (int i=0; i< 5; i++) {
			mockClass.add(new Class(UUID.randomUUID(), "test" + i, UUID.randomUUID()));
		}

		when(classRepository.findAll()).thenReturn(mockClass);

		List<Class> actualClass = classServiceImplement.getAllClasses();

		assertEquals(actualClass.size(), mockClass.size());

		verify(classRepository).findAll();
	}

}
