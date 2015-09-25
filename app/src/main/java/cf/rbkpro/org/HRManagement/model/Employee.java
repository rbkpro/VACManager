package cf.rbkpro.org.HRManagement.model;

import java.util.Date;

/**
 * Employee class.
 */
public class Employee {

    private String first_name ;
    private String  last_name ;
    private Date date_of_birth ;
    private String place_of_birth ;
    private String marital_status ;
    private int childs_number ;
    private String phone_number ;
    private String education_level ;
    private String diploma ;
    private Date date_start_firstjob ;
    private String grade_firstjob ;
    private Date date_join_cf ;
    private String grade_join_cf ;
    private String actual_grade ;

    public Employee(){}

    public Employee(String firstName,String lastName,Date birthDate,String birthPlace, String maritalStatus,int childs,String phoneNumber,
                    String eduLevel,String diploma,Date dateFirstJob,String gradeFirstJob, Date dateJoinCF, String gradeJoinCF, String gradeActual){

        this.first_name=firstName;
        this.last_name=lastName;
        this.date_of_birth=birthDate;
        this.place_of_birth=birthPlace;
        this.marital_status=maritalStatus;
        this.childs_number=childs;
        this.phone_number=phoneNumber;
        this.education_level=eduLevel;
        this.diploma=diploma;
        this.date_start_firstjob = dateFirstJob;
        this.grade_firstjob=gradeFirstJob;
        this.date_join_cf=dateJoinCF;
        this.grade_join_cf=gradeJoinCF;
        this.actual_grade=gradeActual;

    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getPlace_of_birth() {
        return place_of_birth;
    }
    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }
    public String getMarital_status() {
        return marital_status;
    }
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public int getChilds_number() {
        return childs_number;
    }
    public void setChilds_number(int childs_number) {
        this.childs_number = childs_number;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getEducation_level() {
        return education_level;
    }
    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }
    public String getDiploma() {
        return diploma;
    }
    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }
    public Date getDate_start_firstjob() {
        return date_start_firstjob;
    }
    public void setDate_start_firstjob(Date date_start_firstjob) {
        this.date_start_firstjob = date_start_firstjob;
    }
    public String getGrade_firstjob() {
        return grade_firstjob;
    }
    public void setGrade_firstjob(String grade_firstjob) {
        this.grade_firstjob = grade_firstjob;
    }
    public Date getDate_join_cf() {
        return date_join_cf;
    }
    public void setDate_join_cf(Date date_join_cf) {
        this.date_join_cf = date_join_cf;
    }
    public String getGrade_join_cf() {
        return grade_join_cf;
    }
    public void setGrade_join_cf(String grade_join_cf) {
        this.grade_join_cf = grade_join_cf;
    }
    public String getActual_grade() {
        return actual_grade;
    }
    public void setActual_grade(String actual_grade) {
        this.actual_grade = actual_grade;
    }


}
