package be.arlonpromsoc.pac.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.arlonpromsoc.pac.timesheet.dao.Timesheets;

public interface TimesheetRepo extends JpaRepository<Timesheets, Long>{

}
