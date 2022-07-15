package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Activity;

public interface ActivityService {
	public Boolean addNewActivity(Activity activity);
	public Boolean deleteActivity(UUID activityID);
	public List<Activity> getActivity();
	public Activity getActivityByID(UUID activityID);
	public Boolean updateActivity(Activity activity);
}
