public class StaffHire {
    private int vacancyNumber;
	private String designation;
    private String jobType;
    //Using constructor to set the values of instance variables//
    StaffHire (int vacancyNumber, String designation, String jobType) {
        this.vacancyNumber = vacancyNumber;
        this.designation = designation;
        this.jobType = jobType;
    }
    //using getter setter methods to get and set values of instance variables//
    public int getVacancyNumber () {
        return vacancyNumber;
    }

    public void setVacancyNumber (int vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }

    public String getDesignation () {
        return designation;
    }

    public void setDesignation (String designation) {
        this.designation = designation;
    }

    public String getJobType () {
        return jobType;
    }

    public void setJobType (String jobType) {
        this.jobType = jobType;
    }
    //displaying the values of instance variable with annotations//
    public void displayDetails () {
        System.out.print("Vacancy Number: " + getVacancyNumber());
        System.out.print("\nDesignation: " + getDesignation());
        System.out.print("\nJob type: " + getJobType());
	}
}
