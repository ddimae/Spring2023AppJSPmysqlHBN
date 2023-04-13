package ntukhpi.semit.dde.webapphbn.entities;

import java.util.ArrayList;

public class EmployeeList extends ArrayList<Employee> {
    public EmployeeList(){
    }
    public EmployeeList(int num) {
        this.add(new Employee(1l,"Rebrov",true,50, 10000000.0));
        this.add(new Employee(2l,"Shevchenko",true,47, 100000.0));
        this.add(new Employee(3l,"Morgul'",false,45, 1000000.0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EmployeeList");
        for (Employee e : this) {
             sb.append(System.lineSeparator()).append(e.toString());
        }
        return sb.toString();
    }
}
