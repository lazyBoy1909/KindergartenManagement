package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "food_menu")
public class FoodMenu {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "food_menu_id")
	private UUID foodMenuID;
	@Column(name = "date_food")
	private Date dateFood;
	@Column(name = "breakfast_food_list")
	private String breakfastFoodList;
	@Column(name = "lunch_food_list")
	private String lunchFoodList;
	@Column(name = "dinner_food_list")
	private String dinnerFoodList;
	public UUID getFoodMenuID() {
		return foodMenuID;
	}
	public void setFoodMenuID(UUID foodMenuID) {
		this.foodMenuID = foodMenuID;
	}
	public Date getDateFood() {
		return dateFood;
	}
	public void setDateFood(Date dateFood) {
		this.dateFood = dateFood;
	}
	public String getBreakfastFoodList() {
		return breakfastFoodList;
	}
	public void setBreakfastFoodList(String foodList) {
		this.breakfastFoodList = foodList;
	}
	
	public String getLunchFoodList() {
		return lunchFoodList;
	}
	public void setLunchFoodList(String lunchFoodList) {
		this.lunchFoodList = lunchFoodList;
	}
	public String getDinnerFoodList() {
		return dinnerFoodList;
	}
	public void setDinnerFoodList(String dinnerFoodList) {
		this.dinnerFoodList = dinnerFoodList;
	}
	public FoodMenu() {
		super();
	}
	public FoodMenu(UUID foodMenuID, Date dateFood, String breakfastFoodList, String lunchFoodList,
			String dinnerFoodList) {
		super();
		this.foodMenuID = foodMenuID;
		this.dateFood = dateFood;
		this.breakfastFoodList = breakfastFoodList;
		this.lunchFoodList = lunchFoodList;
		this.dinnerFoodList = dinnerFoodList;
	}
	
	
	
}
