package be.arlonpromsoc.pac.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.arlonpromsoc.pac.timesheet.dao.Activity;
import be.arlonpromsoc.pac.timesheet.repository.ActivityRepo;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityRepo repo;
	
	@Override
	@Transactional
	public Activity addActivity(Activity activity) {
		return this.repo.save(activity);
	}
}
