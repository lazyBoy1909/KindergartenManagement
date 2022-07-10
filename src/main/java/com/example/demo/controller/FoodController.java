package com.example.demo.controller;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FoodMenu;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.FoodMenuService;

@RestController
@RequestMapping("/foodMenu")
public class FoodController {
	@Autowired
	FoodMenuService foodMenuService;
	
	@GetMapping(path = "/getFoodMenu")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getFoodMenu(@RequestParam("timeStart") Date timeStart, @RequestParam("timeEnd") Date timeEnd)
	{
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "List of food menus", foodMenuService.getFoodMenu(timeStart, timeEnd)));
	}

	
	@PostMapping(path = "/addFoodMenu")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addFoodMenu(@RequestBody FoodMenu foodMenu)
	{
		int res = foodMenuService.addFoodMenu(foodMenu);
		if(res == -1)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Cannot add menu for the past",res));
		}
		else if(res == 0)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Menu for this day existed",res));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Add menu successfully",res));
		}

	}
	@DeleteMapping(path = "/deleteFoodMenu")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteFoodMenu(@RequestParam("foodMenuID") UUID foodMenuID)
	{
		foodMenuService.deleteFoodMenu(foodMenuID);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Delete menu successfully",true));
	}
	
	@GetMapping(path ="/getMenuFoodByID")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_PARENT', 'ROLE_ADMIN')")
	public ResponseEntity<?> getMenuFoodByID(@RequestParam("foodMenuID") UUID foodMenuID)
	{
		FoodMenu foodMenu = foodMenuService.getFoodMenuByID(foodMenuID);
		if(foodMenu != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Food Menu's information",foodMenu));

		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Failed", "Invalid input",foodMenu));

		}
	}
}
