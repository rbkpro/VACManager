package cf.rbkpro.org.HRManagement;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import cf.rbkpro.org.HRManagement.model.Employee;
import cf.rbkpro.org.HRManagement.adapters.EmployeeListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    public static final String sectionView = "Page";
    private ArrayList<Employee> employeeList ;
    private ArrayList<Employee> subListEmployee;
    private EmployeeListAdapter employeeListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View view;
    private String bundel;
    private int end, start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employees, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            employeeListAdapter = new EmployeeListAdapter(employeeList, inflater);
            bundel=bundle.getString(sectionView);
            refreshValues(bundel);
             mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.red);
            mSwipeRefreshLayout.setOnRefreshListener(this);
            ListView employeeList = (ListView) view.findViewById(R.id.employee_list);
            employeeList.setAdapter(employeeListAdapter);
            employeeList.setOnItemClickListener(this);
        }
        return view;
    }

    public void refreshValues(String bundel){
        employeeList = new ArrayList<Employee>(MainActivity.getEmployees().values());
        //Sort employee List by date out vacation
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                if (emp1.getDate_of_birth().before(emp2.getDate_of_birth())) {
                    return -1;
                } else return 1;
            }
        });
        if ( bundel== "all employees") {
            employeeListAdapter.updateData(employeeList);
        } else {
            start=getListLimits()[0];end =getListLimits()[1];
            subListEmployee = new ArrayList<Employee>(employeeList.subList(start, end));
            employeeListAdapter.updateData(subListEmployee);
            //LinearLayout mLayout= (LinearLayout) view.findViewById(R.id.mlayout);
            //mLayout.setBackgroundResource(R.drawable.bg2);
        }
    }

    @Override
    public void onRefresh() {
        refreshValues(bundel);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        if(id== R.id.action_refresh){
            refreshValues(bundel);
        }
        return super.onOptionsItemSelected(item);
    }

    public int[] getListLimits() {
        end = 0;
        start = 0;
        int[] subLimits = new int[2];
        for (Employee emp : employeeList) {
            if (emp.getDate_of_birth().before(new Date())) {
                end++;
                Calendar c = new GregorianCalendar();
                c.setTime(emp.getDate_of_birth());
                c.add(c.DATE, 30);
                Date returnDate = c.getTime();
                if (returnDate.before(new Date())) {
                    start++;
                }
            }
        }
        subLimits[0] = start;
        subLimits[1] = end;
        return subLimits;
    }

    private void displayEmployeeInfo(Employee employee) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Information");
        alert.setMessage(getString(R.string.info_message) + "\n \n" + employee.getLast_name() + "  " + employee.getFirst_name() + "\n \n"
                       +"الرتبة الحالية : "+ employee.getActual_grade()+"\n \n"+"الشهادة : "+ employee.getDiploma());
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Employee employee;
        employee = (Employee) employeeListAdapter.getItem(position);
        displayEmployeeInfo(employee);
    }
}
