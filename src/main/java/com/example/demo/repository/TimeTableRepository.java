package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, UUID> {

	@Query(value = "select * from time_table where class_id =:classID", nativeQuery = true)
	public List<TimeTable> getTimeTableByClassID(@Param("classID") UUID classID);
}
