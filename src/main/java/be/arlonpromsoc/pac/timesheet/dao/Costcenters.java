package be.arlonpromsoc.pac.timesheet.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COST_CENTER")
public class Costcenters {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "costCenter")
	private List<Timesheets> timesheets;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Timesheets> getTimesheets() {
		return timesheets;
	}


	public void setTimesheets(List<Timesheets> timesheets) {
		this.timesheets = timesheets;
	}
	
	
	
}
