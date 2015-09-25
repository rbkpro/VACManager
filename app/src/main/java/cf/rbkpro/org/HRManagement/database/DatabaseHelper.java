package cf.rbkpro.org.HRManagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cf.rbkpro.org.HRManagement.database.CFdatabaseContract.FeedEmployee;
import cf.rbkpro.org.HRManagement.model.Employee;


/**
Create a SqlLite DataBase  */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static Map<Long, Employee> employees=  new HashMap<>();
    private static final String QUERY_URL="http://192.168.1.3:8080/messenger/resources/employees";

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
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_BIRTH + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_PLACE_BIRTH + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_MARITAL_STATUS + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_CHILDS_NUMBER + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_EDUCATION_LEVEL + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_DIPLOMA + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_FIRSTJOB + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_JOINCF + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_JOINCF + TEXT_TYPE + COMMA_SEP +
                    FeedEmployee.COLUMN_NAME_EMPLOYEE_ACTUAL_GRADE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_EMPLOYEE =
            "DROP TABLE IF EXISTS " + FeedEmployee.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  Map<Long,Employee> getEmployees(){
        SQLiteDatabase db = this.getReadableDatabase();
        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                FeedEmployee._ID,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_BIRTH,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_PLACE_BIRTH,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_MARITAL_STATUS,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_CHILDS_NUMBER,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_PHONE_NUMBER,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_EDUCATION_LEVEL,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_DIPLOMA,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_FIRSTJOB,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_JOINCF,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_JOINCF,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_ACTUAL_GRADE
        };
        // How you want the results sorted in the resulting Cursor
        Cursor c = db.query(FeedEmployee.TABLE_NAME, projection, null, null, null, null, null );
        //Log.i("ApplicationCF","MainActivity getEmployees cursor size:" + c.getCount());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        if(c!=null && c.moveToFirst()){
            do {
                try {
                    employees.put(c.getLong(c.getColumnIndex(FeedEmployee._ID)),
                            new Employee(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME)),
                                    df.parse(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_BIRTH))),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_PLACE_BIRTH)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_MARITAL_STATUS)),
                                    c.getInt(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_CHILDS_NUMBER)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_PHONE_NUMBER)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_EDUCATION_LEVEL)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_DIPLOMA)),
                                    df.parse(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB))),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_FIRSTJOB)),
                                    df.parse(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB))),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_JOINCF)),
                                    c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_ACTUAL_GRADE))
                            )
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while(c.moveToNext() );
            c.close();
        }
        Log.i("ApplicationCF", "DatabaseHelper getEmployees result size :" + employees.size());
        return employees;
    }

    public void initDatabase(ArrayList<Employee> emps){
        SQLiteDatabase db= this.getWritableDatabase();
        int i=0;
        ContentValues values= new ContentValues();
        for(Employee em: emps){
            values.put(CFdatabaseContract.FeedEmployee._ID,i++);
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME, em.getFirst_name());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME,em.getLast_name());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_BIRTH,new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(em.getDate_of_birth()));
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_PLACE_BIRTH, em.getPlace_of_birth());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_MARITAL_STATUS,em.getMarital_status());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_CHILDS_NUMBER,em.getChilds_number());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_PHONE_NUMBER,em.getPhone_number());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_EDUCATION_LEVEL,em.getEducation_level());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_DIPLOMA,em.getDiploma());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_FIRSTJOB,new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(em.getDate_start_firstjob()));
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_FIRSTJOB,em.getGrade_firstjob());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_JOINCF,new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(em.getDate_join_cf()));
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_GRADE_JOINCF,em.getGrade_join_cf());
            values.put(CFdatabaseContract.FeedEmployee.COLUMN_NAME_EMPLOYEE_ACTUAL_GRADE,em.getActual_grade());
            db.insert(CFdatabaseContract.FeedEmployee.TABLE_NAME, CFdatabaseContract.FeedEmployee._ID, values);

        }
        db.close();
    }

    public void getDataFromServer() {
        AsyncHttpClient client= new AsyncHttpClient();
        //client.setTimeout(100000);
        client.get(QUERY_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONArray timeline) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                try {
                    for (int i = 0; i < timeline.length(); i++) {
                        Employee emp = mapper.readValue(timeline.get(i).toString(), Employee.class);
                        employees.put(i + 1L, emp);
                    }
                    initDatabase(new ArrayList<Employee>(employees.values()));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.e("ApplicationCF", statusCode + " getDataFromServer onFailure :" + throwable.getMessage());
            }
        });
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
