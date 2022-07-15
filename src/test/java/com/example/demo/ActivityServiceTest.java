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

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.service.ActivityServiceImplement;


@SpringBootTest
public class ActivityServiceTest {
    @Mock
	ActivityRepository activityRepository;

	@InjectMocks
	ActivityServiceImplement activityServiceImplement;
	
	@Test
	void testGetAllActivity() {
		List<Activity> mockActivitys = new ArrayList<>();
		for (int i=0; i< 5; i++) {
			mockActivitys.add(new Activity());
		}

		when(activityRepository.findAll()).thenReturn(mockActivitys);
        
		List<Activity> actualClass = activityServiceImplement.getActivity();

		assertEquals(actualClass.size(), mockActivitys.size());

		verify(activityRepository).findAll();
	}
}
