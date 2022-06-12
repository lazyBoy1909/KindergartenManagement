package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Class;
@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {

	@Query(value = "Select class.class_id, class.teacher_id, class.class_name from class, student where student.class_id = class.class_id and student.student_id =:studentID", nativeQuery = true)
	public Class getClassByStudentID(@Param("studentID") UUID studentID);
	@Query(value = "Select * from class where teacher_id =:teacherID", nativeQuery = true)
	public List<Class> getClassIDByTeacherID(@Param("teacherID") UUID teacherID);
}
