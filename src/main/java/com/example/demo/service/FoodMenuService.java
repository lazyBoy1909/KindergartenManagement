package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.example.demo.model.FoodMenu;

public interface FoodMenuService {
	public List<FoodMenu> getFoodMenu(Date timeStart, Date timeEnd);
	public int addFoodMenu(FoodMenu foodMenu);
	public Boolean deleteFoodMenu(UUID foodMenuID);
	public FoodMenu getFoodMenuByID(UUID foodMenuID);
}
