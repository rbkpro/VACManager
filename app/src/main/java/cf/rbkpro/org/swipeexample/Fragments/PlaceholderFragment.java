package cf.rbkpro.org.swipeexample.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import cf.rbkpro.org.swipeexample.MainActivity;
import cf.rbkpro.org.swipeexample.model.Employee;
import cf.rbkpro.org.swipeexample.R;
import cf.rbkpro.org.swipeexample.adapters.EmployeeListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public static final String descriptionKey="descriptionKey";
    public static final String sectionView="Page";
    private ArrayList<Employee> employeeList=new ArrayList<Employee>(MainActivity.getEmployees().values());;
    private ArrayList<Employee> subListEmployee;
    private EmployeeListAdapter employeeListAdapter;
    private int end,start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
        Bundle bundle= getArguments();
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                if (emp1.getVacationDate().before(emp2.getVacationDate())) {
                    return -1;
                } else return 1;
            }
        });


        if(bundle!=null){
            String secView= bundle.getString(sectionView);
            if(secView=="all employees")
                employeeListAdapter=new EmployeeListAdapter(employeeList,inflater);
            else{
                start = getLimiteList()[0]; end = getLimiteList()[1];
                subListEmployee = new ArrayList<Employee>(employeeList.subList(start,end));
                employeeListAdapter= new EmployeeListAdapter(subListEmployee,inflater);
            }
            String description= bundle.getString(descriptionKey);
            setValue(view,description,inflater);
        }
        return view;
    }

    private void setValue(View view, String description,LayoutInflater inflater) {
        TextView textView=(TextView)    view.findViewById(R.id.tab_description);
        textView.setText(description);
        ListView employeeList= (ListView) view.findViewById(R.id.employee_list);
        employeeList.setAdapter(employeeListAdapter);
    }

    public int[] getLimiteList() {
        end=0;   start=0;
        int[] subLimits=new int[2];
        for(Employee emp : employeeList){
            if(emp.getVacationDate().before(new Date())) {
                end++;
                Calendar c=new GregorianCalendar();
                c.setTime(emp.getVacationDate());
                c.add(c.DATE, 30);
                Date returnDate = c.getTime();
                if(returnDate.before(new Date())){ start++;}
            }
        }
        subLimits[0]=start; subLimits[1]=end;
        return subLimits;
    }
}
