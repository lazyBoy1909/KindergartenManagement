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
	@Column(name = "food_list")
	private String foodList;
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
	public String getFoodList() {
		return foodList;
	}
	public void setFoodList(String foodList) {
		this.foodList = foodList;
	}
	public FoodMenu(UUID foodMenuID, Date dateFood, String foodList) {
		super();
		this.foodMenuID = foodMenuID;
		this.dateFood = dateFood;
		this.foodList = foodList;
	}
	public FoodMenu() {
		super();
	}
	
	
}
