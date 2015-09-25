package cf.rbkpro.org.HRManagement.database;

import android.provider.BaseColumns;

/**
 * My database container /schema
 */
public class CFdatabaseContract {


    public CFdatabaseContract() {}

    public static abstract class FeedEmployee implements BaseColumns{
        public static final String TABLE_NAME = "employee";
        public static final String COLUMN_NAME_EMPLOYEE_FIRSTNAME = "first_name";
        public static final String COLUMN_NAME_EMPLOYEE_LASTNAME = "last_name";
        public static final String COLUMN_NAME_EMPLOYEE_DATE_BIRTH="date_of_birth";
        public static final String COLUMN_NAME_EMPLOYEE_PLACE_BIRTH = "place_of_birth";
        public static final String COLUMN_NAME_EMPLOYEE_MARITAL_STATUS = "marital_status";
        public static final String COLUMN_NAME_EMPLOYEE_CHILDS_NUMBER="childs_number";
        public static final String COLUMN_NAME_EMPLOYEE_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_NAME_EMPLOYEE_EDUCATION_LEVEL = "education_level";
        public static final String COLUMN_NAME_EMPLOYEE_DIPLOMA="diploma";
        public static final String COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB = "date_start_firstjob";
        public static final String COLUMN_NAME_EMPLOYEE_GRADE_FIRSTJOB = "grade_firstjob";
        public static final String COLUMN_NAME_EMPLOYEE_DATE_JOINCF="date_join_cf";
        public static final String COLUMN_NAME_EMPLOYEE_GRADE_JOINCF = "grade_join_cf";
        public static final String COLUMN_NAME_EMPLOYEE_ACTUAL_GRADE = "actual_grade";


    }
}
