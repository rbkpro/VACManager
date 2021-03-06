package cf.rbkpro.org.HRManagement.adapters;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cf.rbkpro.org.HRManagement.R;
import cf.rbkpro.org.HRManagement.model.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class EmployeeListAdapter extends BaseAdapter {

    ArrayList<Employee> employeeList;
    LayoutInflater mInflater;


    public EmployeeListAdapter(ArrayList<Employee> employeeList, LayoutInflater inflater){
        mInflater=inflater;
        this.employeeList=employeeList;

    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView= mInflater.inflate(R.layout.row_employee,null);
            holder=new ViewHolder();
            holder.firstName=(TextView)convertView.findViewById(R.id.first_name);
            holder.lastName=(TextView)convertView.findViewById(R.id.last_name);
            holder.dateOut= (TextView) convertView.findViewById(R.id.date_out);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        String firstName;
        String lastName;
        Date dateOut;
        firstName= employeeList.get(position).getFirst_name();
        lastName=employeeList.get(position).getLast_name();
        dateOut= employeeList.get(position).getDate_of_birth();
        holder.firstName.setText(firstName);
        holder.lastName.setText(lastName);
        holder.dateOut.setText("" + new SimpleDateFormat("yyyy/MM/dd").format(dateOut));
        //Employees who their vacation end or they on vacation or they don't take vacation yet
        if(dateOut.before(new Date())){
            Calendar c=new GregorianCalendar();
            c.setTime(dateOut);
            c.add(c.DATE, 30);
            Date returnDate = c.getTime();
            if(returnDate.before(new Date())){
                holder.dateOut.setTextColor(Color.GRAY);
            }else holder.dateOut.setTextColor(Color.RED);
        }
        else{
             holder.dateOut.setTextColor(Color.GREEN);
        }
        return convertView;
    }

    public void updateData(ArrayList<Employee> empsList){
        employeeList=empsList;
        notifyDataSetChanged();
    }


    private static class ViewHolder {
        public TextView firstName;
        public TextView lastName;
        public TextView dateOut;
    }
}
