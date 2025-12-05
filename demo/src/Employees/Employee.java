package Employees;

public class Employee {
    private String employee_id;
    private String name;
    private String department;
    private float salary;
    private String level;

    public Employee(String employee_id, String name, String department, float salary, String level) {
        this.employee_id = employee_id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.level = level;
    }

    public String getEmployeeId() {
        return employee_id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    public String getLevel() {
        return level;
    }

    public void getDetails() {
        System.out.println("Employee ID: " + employee_id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary + ", Level: " + level); 
    }


}




