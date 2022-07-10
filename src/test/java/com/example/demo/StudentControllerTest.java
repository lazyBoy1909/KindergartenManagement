package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Student;
import com.example.demo.model.Class;
import com.example.demo.model.Parent;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.ParentRepository;
import com.example.demo.service.StudentServiceImplement;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImplement service;
    
    @Mock
    ClassRepository classRepository;

    @Mock
    ParentRepository parentRepository;

    @Test
    void testGetAllStudentForbidden() throws Exception {
        List<Student> listStudents = new ArrayList<Student>();
        Student std = new Student();
        std.setStudentName("Test Name");

        when(service.getAllStudentInfor()).thenReturn(listStudents);

        mockMvc.perform(get("/student/allStudentInfor"))
                .andExpect(status().isForbidden());
    
    }

    @Test
    void testGetAllStudent() throws Exception {
        List<Student> listStudents = new ArrayList<Student>();
        Student std = new Student();
        std.setStudentName("Test Name");

        when(service.getAllStudentInfor()).thenReturn(listStudents);

        mockMvc.perform(get("/student/allStudentInfor").with(user("user").roles("ADMIN")))
                .andExpect(status().isOk());
    
    }

    @Test
    void testPostStudentBadRequest() throws Exception {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setStudentName("Test Name");
        ObjectMapper mapper = new ObjectMapper();
        when(service.getStudentInfor(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"))).thenReturn(std);

        mockMvc.perform(post("/student/addNewStudent")
            .content(mapper.writeValueAsString(std))
            .contentType(MediaType.APPLICATION_JSON)
            .with(user("user").roles("ADMIN")))
            .andExpect(status().isBadRequest());
    }

}
