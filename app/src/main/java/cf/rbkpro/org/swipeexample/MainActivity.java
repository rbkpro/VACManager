package cf.rbkpro.org.swipeexample;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cf.rbkpro.org.swipeexample.adapters.SectionsPagerAdapter;
import cf.rbkpro.org.swipeexample.database.CFdatabaseContract;
import cf.rbkpro.org.swipeexample.database.CFdatabaseContract.FeedEmployee;
import cf.rbkpro.org.swipeexample.database.DataBaseClass;
import cf.rbkpro.org.swipeexample.database.DatabaseHelper;
import cf.rbkpro.org.swipeexample.model.Employee;


public class MainActivity extends ActionBarActivity {

    private static Map<Long, Employee> employees=  new HashMap<>();
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    static DatabaseHelper dbHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the all primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout mTabLayout= (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        dbHelper= new DatabaseHelper(this);
        initDatabase();

    }

    public void initDatabase(){
        SQLiteDatabase db= dbHelper.getWritableDatabase();;
        int i=0;
        ContentValues values= new ContentValues();
       for(Employee em: DataBaseClass.getEmployees()){
           values.put(FeedEmployee._ID,i++);
           values.put(FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME, em.getFirstName());
           values.put(FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME,em.getLastName());
           values.put(FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_VACATION,new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(em.getVacationDate()));
           long newRowid;
           newRowid = db.insert(FeedEmployee.TABLE_NAME, FeedEmployee._ID, values);
           Log.i("MyDatabase TEST CF ", "Insert row id :"+newRowid);
       }
        db.close();
    }


    public static Map<Long,Employee> getEmployees(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                FeedEmployee._ID,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME,
                FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_VACATION
        };
        // How you want the results sorted in the resulting Cursor
        Cursor c = db.query(FeedEmployee.TABLE_NAME, projection, null, null, null, null, null );
        c.moveToFirst();
        Log.i("DatabaseSize ", " Cursor size: " + c.getCount());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        do {

            try {
                employees.put(c.getLong(c.getColumnIndex(FeedEmployee._ID)),
                        new Employee(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_FIRSTNAME)),
                                     c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_LASTNAME)),
                                     df.parse(c.getString(c.getColumnIndex(FeedEmployee.COLUMN_NAME_EMPLOYEE_DATE_VACATION))))
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }while(c.moveToNext() );
        Log.i("DatabaseSize "," arraylist size: "+employees.size());
        return employees;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
