package com.example.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FoodMenu;
import com.example.demo.repository.FoodMenuRepository;

@Service
public class FoodMenuServiceImplement implements FoodMenuService {

	@Autowired
	FoodMenuRepository foodMenuRepository;
	@Override
	public List<FoodMenu> getFoodMenu(Date timeStart, Date timeEnd) {
		List<FoodMenu> listOfFoodMenus = new ArrayList<FoodMenu>();
		listOfFoodMenus = foodMenuRepository.getAllFoodMenuDuringAPeriod(timeStart, timeEnd);
		return listOfFoodMenus;
	}
	@Override
	public int addFoodMenu(FoodMenu foodMenu) {
		if(foodMenu.getDateFood().getTime() < System.currentTimeMillis())
		{
			return -1;
		}
		if(foodMenuRepository.getFoodMenuByDate(foodMenu.getDateFood()) != null) return 0;
		foodMenuRepository.save(foodMenu);
		return 1;
	}
	@Override
	public Boolean deleteFoodMenu(UUID foodMenuID) {
		foodMenuRepository.deleteById(foodMenuID);
		return true;
	}
	@Override
	public FoodMenu getFoodMenuByID(UUID foodMenuID) {
		try
		{
			FoodMenu foodMenu = foodMenuRepository.findById(foodMenuID).get();
			return foodMenu;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
