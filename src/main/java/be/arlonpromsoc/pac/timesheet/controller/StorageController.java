package be.arlonpromsoc.pac.timesheet.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.arlonpromsoc.pac.timesheet.dao.Activity;
import be.arlonpromsoc.pac.timesheet.dao.Costcenters;
import be.arlonpromsoc.pac.timesheet.dao.Employees;
import be.arlonpromsoc.pac.timesheet.dao.Projects;
import be.arlonpromsoc.pac.timesheet.dao.RequestToStore;
import be.arlonpromsoc.pac.timesheet.dao.Timesheets;
import be.arlonpromsoc.pac.timesheet.repository.ActivityRepo;
import be.arlonpromsoc.pac.timesheet.service.StoreService;

@Controller
public class StorageController  {
	
	private final Logger LOGGER = LoggerFactory.getLogger(StorageController.class);
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	ActivityRepo activityRepository;
	
	@Autowired
	MqttClient handler;
	
	@Autowired
	ObjectMapper mapper; 
	
	public void storeActivity (String message) {
		Activity activity;
		try {
			activity = mapper.readValue(message, Activity.class);
			activityRepository.save(activity);
		} catch (Exception e) {
			LOGGER.error("cannot save activity");
		} 
	}
	
	 
	public void storeMessage (String message){
		
		try {
			RequestToStore readValue = mapper.readValue(message, RequestToStore.class);
			storeService.storeRequest(readValue);
			LOGGER.error("Message stored");
		} catch (Exception e1) {
			LOGGER.error("Cannot deserialize message");
		} 
//		Projects project = new Projects();
//		Activity activity = new Activity();
//		Employees employee = new Employees();
//		Timesheets timesheet = new Timesheets();
//		Costcenters center = new Costcenters();
//		
//		List<Activity> activities= new ArrayList<Activity>();
//		List<Projects> projects = new ArrayList<Projects>();
//		List<Timesheets> timesheetList = new ArrayList<Timesheets>();
//		List<Employees> employeeList = new ArrayList<Employees>();
//		
//		activity.setActivityCode(2L);
//		activity.setStart(new Date(150000));
//		activity.setEnd(new Date(100000000));
//		activity.setComments("second comment");
//		
//		employee.setComments(" a good employee");
//		employee.setEnd(new Date(20));
//		employee.setProjects(projects);
//		employee.setStart(new Date(40));
//		employee.setTimesheets(timesheetList);
//		
//		project.setActivities(activities);
//		//project.setEmployee(employeeList);
//		project.setName("un project name");
//		
//		timesheet.setActivity(activity);
//		timesheet.setComment("hard to work ");
//		timesheet.setEmployee(employee);
//		timesheet.setCostCenter(new Costcenters());
//		timesheet.setEndDate(new Date(2500));
//		timesheet.setStartDate(new Date(3600));
//		
//		center.setTimesheets(timesheetList);
//		activities.add(activity);
//		projects.add(project);
//		employeeList.add(employee);
//		
//		RequestToStore  req = new RequestToStore();
//		req.setActivity(activity);
//		req.setEmployee(employee);
//		req.setProject(project);
//		req.setTimesheet(timesheet);
//		req.setCostCenter(center);
		
		
//		try {
//			System.out.println(mapper.writeValueAsString(req));
//			System.out.println(mapper.writeValueAsString(activity));
//		} catch (JsonProcessingException e) {
//			LOGGER.error("Cannot add message");
//		}			
	}	
}
