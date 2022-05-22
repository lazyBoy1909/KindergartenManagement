package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
	@Query(value = "select * from teacher where teacher_id =:teacherID", nativeQuery = true)
	Teacher getTeacherByID(@Param("teacherID") UUID teacherID);
}
