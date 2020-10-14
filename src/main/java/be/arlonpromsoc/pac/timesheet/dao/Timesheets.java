package be.arlonpromsoc.pac.timesheet.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TIMESHEETS")
public class Timesheets {
	
	int activityCodeId;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	Date startDate;
	
	Date endDate;
		
	String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ACTIVITY_ID")
	private Activity activity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="CENTER_ID")
	private Costcenters costCenter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private Employees employee;

	public int getActivityCodeId() {
		return activityCodeId;
	}

	public void setActivityCodeId(int activityCodeId) {
		this.activityCodeId = activityCodeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Costcenters getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Costcenters costCenter) {
		this.costCenter = costCenter;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

}
