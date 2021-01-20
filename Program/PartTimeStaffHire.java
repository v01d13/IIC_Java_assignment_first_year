public class PartTimeStaffHire extends StaffHire {
	private int workingHour;
	private int wagesPerHour;
	private String staffName;
	private String joiningDate;
	private String qualification;
	private String appointedBy;
	private String shifts;
	private boolean joined;
	private boolean terminated;
	
	//Using constructor to set the values and initialize instance variables//
	PartTimeStaffHire (int vacancyNumber, String designation, String jobType, int workingHour, int wagesPerHour, String shifts) {
		//calling constructor of parent class and passing the value of vacancyNumber, designation, jobType//
		super(vacancyNumber, designation, jobType);

		this.workingHour = workingHour;
		this.wagesPerHour = wagesPerHour;
		this.shifts = shifts;

		this.staffName = "";
		this.joiningDate = "";
		this.qualification = "";
		this.appointedBy = "";
		this.joined = false;
		this.terminated = false;
	}
	
	//getter setter methods to get and set the values of instance variables//
	public int getWorkingHour () {
		return workingHour;
	}
	public void setWorkingHour (int workingHour) {
		this.workingHour = workingHour;
	}

	public int getWagesPerHour () {
		return wagesPerHour;
	}
	public void setWagesPerHour (int wagesPerHour) {
		this.wagesPerHour = wagesPerHour;
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

	public String getShifts () {
		return shifts;
	}
	public void setShifts (String shifts) {
		//using if else to check if the staff is hired and setting the shifts if they have not joined//
		if (getJoined() == false) {
			this.shifts = shifts;
		}
		else {
			System.out.print("Error!!! Staff has already been hired, cannot change shifts.");
		}
	}

	public boolean getJoined () {
		return joined;
	}
	public void setJoined (boolean joined) {
		this.joined = joined;
	}

	public boolean getTerminated () {
		return terminated;
	}
	public void setTerminated (boolean terminated) {
		this.terminated = terminated;
	}

	public void hirePartTimeStaff (String staffName, String joiningDate, String qualification, String appointedBy) {
		//setting the staff name, join date, qualification, appointed by if they have not joined yet, if they have joined outputting their name and join date//
		if (getJoined() == false) {
			setStaffName(staffName);
			setJoiningDate(joiningDate);
			setQualification(qualification);
			setAppointedBy(appointedBy);
			setJoined (true);
		}
		else {
			System.out.print("\nStaff has already been hired.");
			System.out.print("\nStaff name: " + getStaffName());
			System.out.print("\nJoined date: " + getJoiningDate());
		}
	}

	public void terminatePartTimeStaff () {
		//checking the termination status of a staff and setting the staff details to empty, termination status to true, and joined to false if not terminated//
		if (getTerminated() == true) {
			System.out.print("\nStaff has already been terminated.");
		}
		else {
			setStaffName("");
			setJoiningDate("");
			setQualification("");
			setAppointedBy("");
			setJoined(false);
			setTerminated(true);
		}
	}

	public void displayDetails () {
		//calling parent class's displayDetails method to display vacancyNumber, designation, jobType//
		super.displayDetails();
		//check if staff has not been terminated yet and display accordingly//
		if (getJoined() == true) {
			int incomePerDay = wagesPerHour * workingHour;

			System.out.print("\nStaff name: " + staffName);
			System.out.print("\nWages per hour: " + getWagesPerHour());
			System.out.print("\nWorking hour: " + getWorkingHour());
			System.out.print("\nJoined date: " + getJoiningDate());
			System.out.print("\nQualification: " + getQualification());
			System.out.print("\nAppointed by: " + getAppointedBy());
			System.out.print("\nIncome per day: " + incomePerDay);
		}
	}

}
