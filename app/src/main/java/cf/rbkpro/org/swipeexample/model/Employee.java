package cf.rbkpro.org.swipeexample.model;

import java.util.Date;

/**
 * Employee class.
 */
public class Employee {
    String firstName;
    String lastName;
    Date vacationDate;

    public Employee(){}

    public Employee(String firstName, String lastName, Date dateOut){
        this.firstName=firstName;
        this.lastName=lastName;
        this.vacationDate=dateOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(Date vacationDate) {
        this.vacationDate = vacationDate;
    }
}
