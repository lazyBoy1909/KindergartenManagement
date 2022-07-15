package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Parent;
import com.example.demo.repository.ParentRepository;
import com.example.demo.service.ParentServiceImplement;


@SpringBootTest
public class ParentServiceTest {
    @Mock
	ParentRepository parentRepository;

	@InjectMocks
	ParentServiceImplement parentServiceImplement;
	
	@Test
	void testGetAllParent() {
		List<Parent> mockParents = new ArrayList<>();
		for (int i=0; i< 5; i++) {
			mockParents.add(new Parent());
		}

		when(parentRepository.findAll()).thenReturn(mockParents);
        
		List<Parent> actualClass = parentServiceImplement.getAllParents();

		assertEquals(actualClass.size(), mockParents.size());

		verify(parentRepository).findAll();
	}
}
