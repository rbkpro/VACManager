package cf.rbkpro.org.HRManagement;



import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Map;

import cf.rbkpro.org.HRManagement.adapters.PagerAdapter;
import cf.rbkpro.org.HRManagement.database.DatabaseHelper;
import cf.rbkpro.org.HRManagement.model.Employee;


public class MainActivity extends AppCompatActivity {

    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    private static DatabaseHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DatabaseHelper(this);
        // Create the adapter that will return a fragment for each of the all primary sections of the activity.
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout mTabLayout= (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        dbHelper.getDataFromServer();

    }

    public static Map<Long, Employee> getEmployees(){
        return dbHelper.getEmployees();
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
