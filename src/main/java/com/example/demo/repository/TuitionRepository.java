package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tuition;

@Repository
public interface TuitionRepository extends JpaRepository<Tuition, UUID>{
	@Query(value = "Select * from tuition where student_id =:studentID", nativeQuery = true)
	public List<Tuition> getTuitionByStudentID(@Param("studentID") UUID studentID);
}
