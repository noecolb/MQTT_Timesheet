package be.arlonpromsoc.pac.timesheet.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.arlonpromsoc.pac.timesheet.dao.Activity;
import be.arlonpromsoc.pac.timesheet.dao.Costcenters;
import be.arlonpromsoc.pac.timesheet.dao.Employees;
import be.arlonpromsoc.pac.timesheet.dao.Projects;
import be.arlonpromsoc.pac.timesheet.dao.RequestToStore;
import be.arlonpromsoc.pac.timesheet.dao.Timesheets;
import be.arlonpromsoc.pac.timesheet.service.StoreService;

@Controller
public class TimesheetController  {
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	MqttClient handler;
	 
	 
	public void storeMessage (String message){
	
		
		Projects project = new Projects();
		Activity activity = new Activity();
		Employees employee = new Employees();
		Timesheets timesheet = new Timesheets();
		Costcenters center = new Costcenters();
		
		List<Activity> activities= new ArrayList<Activity>();
		List<Projects> projects = new ArrayList<Projects>();
		List<Timesheets> timesheetList = new ArrayList<Timesheets>();
		List<Employees> employeeList = new ArrayList<Employees>();
		
		activity.setActivityCode(2L);
		activity.setStart(new Date(150000));
		activity.setEnd(new Date(100000000));
		activity.setComments("second comment");
		
		employee.setComments(" a good employee");
		employee.setEnd(new Date(20));
		employee.setProjects(projects);
		employee.setStart(new Date(40));
		employee.setTimesheets(timesheetList);
		
		project.setActivities(activities);
		project.setEmployee(employeeList);
		project.setName("un project name");
		
		timesheet.setActivity(activity);
		timesheet.setComment("hard to work ");
		timesheet.setEmployee(employee);
		timesheet.setCostCenter(new Costcenters());
		timesheet.setEndDate(new Date(2500));
		timesheet.setStartDate(new Date(3600));
		
		center.setTimesheets(timesheetList);
		activities.add(activity);
		projects.add(project);
		employeeList.add(employee);
		
		RequestToStore  req = new RequestToStore();
		req.setActivity(activity);
		req.setEmployee(employee);
		req.setProject(project);
		req.setTimesheet(timesheet);
		req.setCostCenter(center);
		
		
		storeService.storeRequest(req);
		
		try {
			responseToMessage("OK");
			System.out.println("ok");
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}
	
	public void responseToMessage (String message ) throws MqttPersistenceException, MqttException {
		System.out.println(message);
		MqttMessage response = new MqttMessage();
		response.setPayload(message.getBytes());
		// MQTT OUTBOUT PRODUCER RESPONSE 
		handler.publish("timesheet", response);

		
	}
	
}
