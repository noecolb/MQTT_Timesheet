package be.arlonpromsoc.pac.timesheet.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY")
public class Activity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ACITIVTY_CODE")
	private Long activityCode;
	
	@Column(name="DATE_START")
	Date start;
	
	@Column(name="DATE_END")
	Date end;
	
	@Column(name="COMMENTS")
	String comments;
	
	// Lazy car chargement employé sur demande
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	private Projects project;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")
	private List<Timesheets> timesheets;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(Long activityCode) {
		this.activityCode = activityCode;
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
	
	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public List<Timesheets> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheets> timesheets) {
		this.timesheets = timesheets;
	}
}
