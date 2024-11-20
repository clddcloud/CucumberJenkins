package utils;

import java.util.List;
import java.util.Map;

public class dbTest {
    public static void main(String[] args) {
        String query="select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id=3707550";
       List<Map<String,String>> data= dbUtils.fetch(query);
       // get the first map (key and values)
        System.out.println(data.get(0));
        // get the value for the "age" key
        System.out.println(data.get(0).get("age"));






    }
}
