package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.Optional;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Student;
import com.example.demo.model.Tuition;
import com.example.demo.model.Class;
import com.example.demo.model.Parent;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.ParentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TuitionRepository;
import com.example.demo.service.StudentServiceImplement;
import com.jayway.jsonpath.Option;

import net.bytebuddy.build.Plugin.Engine.Source.Empty;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
	StudentRepository studentRepository;

    @Mock
	TuitionRepository tuitionRepository;

    @Mock
    ClassRepository classRepository;

    @Mock
    ParentRepository parentRepository;
    
	@InjectMocks
	StudentServiceImplement studentServiceImplement;
	
	@Test
	void testGetAllStudent() {
		List<Student> mockStudents = new ArrayList<>();
		for (int i=0; i< 5; i++) {
			mockStudents.add(new Student());
		}

		when(studentRepository.findAll()).thenReturn(mockStudents);
        
		List<Student> actualClass = studentServiceImplement.getAllStudentInfor();

		assertEquals(actualClass.size(), mockStudents.size());

		verify(studentRepository).findAll();
	}

    @Test
    void testGetAStudent() {

        var std1 = new Student(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"), UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d19"), UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1c19"), new Date(), "studentName", 1);
        
        
        when(studentRepository.findById(std1.getStudentID())).thenReturn(Optional.of(std1));

        var res = studentServiceImplement.getStudentInfor(std1.getStudentID());

        assertEquals(res, std1);

        verify(studentRepository).findById(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
    }

    @Test
    public void testInsertStudent() {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setClassID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d58"));
        std.setParentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d57"));
        std.setStudentName("test");
        
        Class cls = new Class();

        when(classRepository.findById(std.getClassID())).thenReturn(Optional.of(cls));
        when(parentRepository.findById(std.getParentID())).thenReturn(Optional.of(new Parent()));

        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(std);


        boolean created = studentServiceImplement.addNewStudent(std);

        assertEquals(true, created);
        verify(studentRepository).save(std);
    }


    @Test
    public void testInsertStudentWhenClassNotFound() {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setClassID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d58"));
        std.setParentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d57"));
        std.setStudentName("test");
        
        // when(classRepository.findById(std.getClassID())).thenReturn(Optional.empty());
        when(classRepository.findById(std.getClassID())).thenThrow(NoSuchElementException.class);

        boolean created = studentServiceImplement.addNewStudent(std);
        assertEquals(false, created);

    }

    @Test
    public void testInsertStudentThrowException() {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setClassID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d58"));
        std.setParentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d57"));
        std.setStudentName("test");
        
        when(classRepository.findById(std.getClassID())).thenReturn(null);

        assertThrows(Exception.class, () ->  studentServiceImplement.addNewStudent(std));
    }

    @Test
    public void testUpdateStudent() {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setClassID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d58"));
        std.setParentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d57"));
        std.setStudentName("test");
        
        Class cls = new Class();

        when(studentRepository.findById(std.getStudentID())).thenReturn(Optional.of(std));
        when(classRepository.findById(std.getClassID())).thenReturn(Optional.of(cls));
        when(parentRepository.findById(std.getParentID())).thenReturn(Optional.of(new Parent()));

        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(std);


        boolean updated = studentServiceImplement.changeStudentInfor(std);

        assertEquals(true, updated);
        verify(studentRepository).save(std);
    }

    @Test
    public void testUpdateStudentWhenNotFound() {
        Student std = new Student();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setClassID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d58"));
        std.setParentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d57"));
        std.setStudentName("test");
        
        // when(studentRepository.findById(std.getStudentID())).thenReturn(Optional.empty());
        when(studentRepository.findById(std.getStudentID())).thenThrow(NoSuchElementException.class);

        boolean updated = studentServiceImplement.changeStudentInfor(std);

        assertEquals(false, updated);
    }


    @Test
    void testDeleteAStudent(){
        Student std = new Student();
        List<Tuition> tuitionList = new ArrayList<Tuition>();
        std.setStudentID(UUID.fromString("c509be7e-db3e-4c0e-8545-5762a60f1d59"));
        std.setStudentName("test");

        when(tuitionRepository.getTuitionByStudentID(std.getStudentID())).thenReturn(tuitionList);

        studentServiceImplement.deleteStudent(std.getStudentID());
        verify(studentRepository).deleteById(std.getStudentID());
    }


}
