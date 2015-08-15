package cf.rbkpro.org.swipeexample.database;

import cf.rbkpro.org.swipeexample.model.Employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class DataBaseClass {


    private static Map<Long, Employee> employees=  new HashMap<>();
    public DataBaseClass(){

    }


    public static List<Employee> getEmployees() {

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            employees.put(1L, new Employee("فراح", "زيموش", df.parse("19/07/2015")));
            employees.put(2L, new Employee("نبيل", "ناصر", df.parse("19/07/2015")));
            employees.put(3L, new Employee("سهام", "بوحوحو", df.parse("19/07/2015")));

            employees.put(4L, new Employee("حياة", "بن خليفة", df.parse("26/07/2015")));
            employees.put(5L, new Employee("صالح", "رماش", df.parse("26/07/2015")));
            employees.put(6L, new Employee("أمين", "سطاح", df.parse("26/07/2015")));

            employees.put(7L, new Employee("يزيد", "خلاف", df.parse("02/08/2015")));
            employees.put(8L, new Employee("عبد الهادي", "بلفتحي", df.parse("02/08/2015")));
            employees.put(9L, new Employee("سلمى", "معروف", df.parse("02/08/2015")));
            employees.put(10L, new Employee("سليمة", "فرعيشي", df.parse("02/08/2015")));
            employees.put(11L, new Employee("احمد", "بلخطابية", df.parse("02/08/2015")));
            employees.put(12L, new Employee("فضيلة", "بولعيد", df.parse("02/08/2015")));
            employees.put(13L, new Employee("رضوان", "عميمور", df.parse("02/08/2015")));
            employees.put(14L, new Employee("حورية", "عزيز", df.parse("02/08/2015")));
            employees.put(15L, new Employee("احمد", "عاشور", df.parse("02/08/2015")));

            employees.put(16L, new Employee("عماد", "شلية", df.parse("05/08/2015")));

            employees.put(17L, new Employee("نوال", "بن شاشم", df.parse("09/08/2015")));
            employees.put(18L, new Employee("نوارة", "بلكامل", df.parse("09/08/2015")));
            employees.put(19L, new Employee("عماد", "خميسي", df.parse("09/08/2015")));
            employees.put(20L, new Employee("لخضر", "أمقران", df.parse("09/08/2015")));
            employees.put(21L, new Employee("فريدة", "بن لخضر", df.parse("09/08/2015")));
            employees.put(22L, new Employee("حنان", "بزاز", df.parse("09/08/2015")));
            employees.put(23L, new Employee("أسماء", "بودن", df.parse("09/08/2015")));

            employees.put(24L, new Employee("هشام", "زردود", df.parse("16/08/2015")));

            employees.put(25L, new Employee("اسماعيل", "فنوش", df.parse("23/08/2015")));
            employees.put(26L, new Employee("الزبير", "خليفة", df.parse("23/08/2015")));
            employees.put(27L, new Employee("شعيب", "بوحالة", df.parse("23/08/2015")));
            employees.put(28L, new Employee("رابح", "عوقابة", df.parse("23/08/2015")));

            employees.put(29L, new Employee("رحيمة", "بريك", df.parse("26/08/2015")));
            employees.put(30L, new Employee("أميرة", "بلقدري", df.parse("30/08/2015")));
            employees.put(31L, new Employee("عبد المؤمن", "علام", df.parse("01/09/2015")));
            employees.put(32L, new Employee("محمد", "صايفي", df.parse("06/09/2015")));
            employees.put(33L, new Employee("فتيحة", "مسيلي", df.parse("06/09/2015")));
            employees.put(34L, new Employee("حمزة", "لمزري", df.parse("13/09/2015")));
            employees.put(35L, new Employee("نسيمة", "بولحليب", df.parse("27/09/2015")));
            employees.put(36L, new Employee("سمير", "نحال", df.parse("04/10/2015")));
            employees.put(37L, new Employee("زهور", "بودن", df.parse("04/10/2015")));
            employees.put(38L, new Employee("سارة", "صدراتي", df.parse("02/11/2015")));
            employees.put(39L, new Employee("طارق", "عميرة", df.parse("15/11/2015")));
            employees.put(40L, new Employee("نصيرة", "بوسنة", df.parse("29/11/2015")));
            employees.put(41L, new Employee("منال", "ناصري", df.parse("01/12/2015")));

            employees.put(42L,new Employee("زكية", "بوعبدالله", df.parse("23/08/2015")));
        }
        catch (Exception e){
            System.err.print(e);
        }

        return new ArrayList<Employee>(employees.values());
    }

}
