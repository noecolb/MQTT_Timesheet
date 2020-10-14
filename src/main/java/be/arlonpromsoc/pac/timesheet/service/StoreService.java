package be.arlonpromsoc.pac.timesheet.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.arlonpromsoc.pac.timesheet.dao.Activity;
import be.arlonpromsoc.pac.timesheet.dao.Employees;
import be.arlonpromsoc.pac.timesheet.dao.RequestToStore;
import be.arlonpromsoc.pac.timesheet.dao.Timesheets;
import be.arlonpromsoc.pac.timesheet.repository.ActivityRepo;
import be.arlonpromsoc.pac.timesheet.repository.CostCenterRepo;
import be.arlonpromsoc.pac.timesheet.repository.EmployeeRepo;
import be.arlonpromsoc.pac.timesheet.repository.ProjectRepo;
import be.arlonpromsoc.pac.timesheet.repository.TimesheetRepo;

@Service
public class StoreService {
	
	@Autowired
	ObjectMapper parser;
	
	@Autowired
	CostCenterRepo costrepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	TimesheetRepo timesheetRepo;
	
	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	ActivityRepo activityRepo;
	
	Logger logger = LoggerFactory.getLogger(StoreService.class);
	
	
	@Transactional
	public void storeRequest (String message) {
		try {
			RequestToStore req = parser.readValue(message, RequestToStore.class);
			activityRepo.save(req.getActivity());
			employeeRepo.save(req.getEmployee());
			projectRepo.save(req.getProject());
			timesheetRepo.save(req.getTimesheet());
	
		} catch (Exception e) {
			logger.error("Unale to parse store the request" + e);
		} 
	}
	
	@Transactional
	public void storeRequest (RequestToStore req) {
		try {
			projectRepo.save(req.getProject());
			activityRepo.save(req.getActivity());
			employeeRepo.save(req.getEmployee());
			timesheetRepo.save(req.getTimesheet());
			costrepo.save(req.getCostCenter());	
			
		} catch (Exception e) {
			logger.error("Unale to parse store the request" + e);
		}
	}
}
