package be.arlonpromsoc.pac.timesheet.dao;

public class RequestToStore {
	
	private Activity activity; 
	
	private Projects project;
	
	private Timesheets timesheet;
	
	private Employees employee;
	
	private Costcenters costCenter;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public Timesheets getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheets timesheet) {
		this.timesheet = timesheet;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public Costcenters getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Costcenters costCenter) {
		this.costCenter = costCenter;
	}
	
	
}
