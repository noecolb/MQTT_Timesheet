package be.arlonpromsoc.pac.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.arlonpromsoc.pac.timesheet.dao.Activity;

public interface ActivityRepo  extends JpaRepository<Activity, Long>{

}
