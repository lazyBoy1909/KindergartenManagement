package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Class;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

	@Query(value = "Select * from student where class_id =:classID", nativeQuery = true)
	List<Student> findStudentByClassID(@Param("classID") UUID classID);
	
	@Query(value = "Select * from student where student.parent_id =:parentID", nativeQuery = true)
	List<Student> findStudentByParentID(@Param("parentID") UUID parentID);
}
