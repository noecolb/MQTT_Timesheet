package be.arlonpromsoc.pac.timesheet.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECTS")
public class Projects {
	
	
	// TODO generate with UUID 
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME")
	private String name; 
	
	// Lazy car chargement employé sur demande
//	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinTable(name="EMPLOYEE_PROJECT", joinColumns = {@JoinColumn(name="PROJECT_ID")}, inverseJoinColumns = {@JoinColumn(name="EMPLOYEE_ID")} )
//	private List<Employees> employee;
	
	// Charge les projets des qu'un employé est chargé
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private List<Activity> activities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Employees> getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(List<Employees> employee) {
//		this.employee = employee;
//	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	

}
