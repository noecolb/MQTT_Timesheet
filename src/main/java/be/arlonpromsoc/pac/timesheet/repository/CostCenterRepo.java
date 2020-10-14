package be.arlonpromsoc.pac.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.arlonpromsoc.pac.timesheet.dao.Costcenters;

public interface CostCenterRepo extends JpaRepository<Costcenters, Long>{

}
