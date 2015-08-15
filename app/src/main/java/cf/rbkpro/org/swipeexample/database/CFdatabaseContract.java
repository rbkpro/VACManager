package cf.rbkpro.org.swipeexample.database;

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
        public static final String COLUMN_NAME_EMPLOYEE_DATE_VACATION="vacation_date";

    }
}
