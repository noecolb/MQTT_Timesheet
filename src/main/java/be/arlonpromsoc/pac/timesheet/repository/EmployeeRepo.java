package be.arlonpromsoc.pac.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.arlonpromsoc.pac.timesheet.dao.Employees;

public interface EmployeeRepo extends JpaRepository<Employees, Long>{

}
