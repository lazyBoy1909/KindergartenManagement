package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FoodMenu;

@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, UUID> {
	@Query(value = "select* from food_menu f where f.date_food >=:timeStart and f.date_food <=:timeEnd", nativeQuery = true)
	public List<FoodMenu> getAllFoodMenuDuringAPeriod(@Param("timeStart") Date timeStart, @Param("timeEnd") Date timeEnd);
	
	@Query(value = "select* from food_menu f where f.date_food =:dateFood", nativeQuery = true)
	public FoodMenu getFoodMenuByDate(@Param("dateFood") Date dateFood);
}

