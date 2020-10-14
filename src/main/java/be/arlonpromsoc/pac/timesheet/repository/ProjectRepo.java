package be.arlonpromsoc.pac.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.arlonpromsoc.pac.timesheet.dao.Projects;

public interface ProjectRepo extends JpaRepository<Projects, Long>{

}
