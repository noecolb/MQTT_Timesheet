package be.arlonpromsoc.pac.timesheet.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEES")
public class Employees {
		
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="START_DATE")
	Date start;
	
	@Column(name="END_DATE")
	Date end;
	
	@Column(name="COMMENTS")
	String comments;
	
	// Charge les projets des qu'un employé est chargé
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="EMPLOYEE_PROJECT", joinColumns = {@JoinColumn(name="EMPLOYEE_ID")}, inverseJoinColumns = {@JoinColumn(name="PROJECT_ID")} )
	List<Projects> projects;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	List<Timesheets> timesheets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<Projects> getProjects() {
		return projects;
	}

	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}

	public List<Timesheets> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheets> timesheets) {
		this.timesheets = timesheets;
	}
	
	
}
