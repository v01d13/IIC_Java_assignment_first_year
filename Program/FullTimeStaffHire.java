public class FullTimeStaffHire extends StaffHire {
	private int salary;
	private int workingHour;
	private String staffName;
	private String joiningDate;
	private String qualification;
	private String appointedBy;
	private boolean joined;
	//Using constructor to set the values or initialize the instance variables//
	FullTimeStaffHire (int vacancyNumber, String designation, String jobType, int salary, int workingHour) {
		//calling parent class constructor and passing the values of vacancyNumber, designation, jobType//
		super(vacancyNumber, designation, jobType);

		this.salary = salary;
		this.workingHour = workingHour;

		this.staffName = "";
		this.joiningDate = "";
		this.qualification = "";
		this.appointedBy = "";
		this.joined = false;
	}
	//using getter setter methods to get and set values of instance variables//
	public int getSalary () {
		return salary;
	}
	public void setSalary (int salary) {
		//checking if staff has joined or not and setting the salary or outputting error message accordingly//
		if (getJoined() == false) {
			this.salary = salary;
		}
		else {
			System.out.print("\nError!!! Staff has already been appointed, cannot change the salary.");
		}
	}

	public int getWorkingHour () {
		return workingHour;
	}
	public void setWorkingHour (int workingHour) {
		this.workingHour = workingHour;
	}

	public String getStaffName () {
		return staffName;
	}
	public void setStaffName (String staffName) {
		this.staffName = staffName;
	}

	public String getJoiningDate () {
		return joiningDate;
	}
	public void setJoiningDate (String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getQualification () {
		return qualification;
	}
	public void setQualification (String qualification) {
		this.qualification = qualification;
	}

	public String getAppointedBy () {
		return appointedBy;
	}
	public void setAppointedBy (String appointedBy) {
		this.appointedBy = appointedBy;
	}

	public boolean getJoined () {
		return joined;
	}
	public void setJoined (boolean joined) {
		this.joined = joined;
	}

	public void newWorkingHour (int newWorkingHour) {
		if (getJoined() == true) {
			this.workingHour = newWorkingHour;
		}
	}

	public void hireFullTimeStaff (String staffName, String joiningDate, String qualification, String appointedBy) {
		//checking if staff has joined or not, if staff has yet to join setting the values of staff details else outputting a error message//
		if (getJoined() == false) {
			setStaffName(staffName);
			setJoiningDate(joiningDate);
			setQualification(qualification);
			setAppointedBy(appointedBy);
			setJoined(true);
		}
		else {
			System.out.print("\nAlready a full-time staff.");
			System.out.print("\nStaff name: " + getStaffName());
			System.out.print("\nJoined date: " + getJoiningDate());
		}
	}

	public void displayDetails () {
		//calling parent class constructor's displayDetails to output the designation, jobType, vacancyNumber//
		super.displayDetails();

		if (getJoined() == true) {
			System.out.print("\nStaff name: " + getStaffName());
			System.out.print("\nSalary: " + getSalary());
			System.out.print("\nWorking hour: " + getWorkingHour());
			System.out.print("\nJoined date: " + getJoiningDate());
			System.out.print("\nQualification: " + getQualification());
			System.out.print("\nAppointed by: " + getAppointedBy());
		}
	}



	
}
