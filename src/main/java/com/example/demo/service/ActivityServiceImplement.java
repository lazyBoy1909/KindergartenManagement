package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;

@Service
public class ActivityServiceImplement implements ActivityService {

	@Autowired
	ActivityRepository activityRepository;
	@Override
	public Boolean addNewActivity(Activity activity) {
		if(activity.getActivityTime().getTime() < System.currentTimeMillis()) return false;
		activityRepository.save(activity);
		return true;
	}
	@Override
	public Boolean deleteActivity(UUID activityID) {
		Activity activity;
		try
		{
			activity = activityRepository.findById(activityID).get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
		activityRepository.delete(activity);
		return true;
	}
	@Override
	public List<Activity> getActivity() {
		return activityRepository.findAll();
	}
	@Override
	public Activity getActivityByID(UUID activityID) {
		try
		{
			Activity activity = activityRepository.findById(activityID).get();
			return activity;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Boolean updateActivity(Activity activity) {
		try {
			Activity foundActivity = activityRepository.findById(activity.getActivityID()).get();
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
		activityRepository.save(activity);
		return true;
	}

}
