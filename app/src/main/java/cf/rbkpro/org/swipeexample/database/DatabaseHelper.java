package cf.rbkpro.org.swipeexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cf.rbkpro.org.swipeexample.database.CFdatabaseContract.FeedEmployee;


/**
Create a SqlLite DataBase  */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="cf43.db";
    public static final String TABLE_NAME="employee";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_EMPLOYEE =
            "CREATE TABLE " + FeedEmployee.TABLE_NAME + " (" +
                    FeedEmployee._ID + " INTEGER PRIMARY KEY," +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_VACATION + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_EMPLOYEE =
            "DROP TABLE IF EXISTS " + FeedEmployee.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EMPLOYEE);
        onCreate(db);
    }
}
