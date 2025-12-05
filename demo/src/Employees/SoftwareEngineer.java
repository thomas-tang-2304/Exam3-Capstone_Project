package Employees;
import Employees.Employee;


public class SoftwareEngineer extends Employee {
    private String programmingLanguage;
    private String branch;

    public SoftwareEngineer(String employee_id, String name, String department, float salary, String level, String programmingLanguage, String branch) {
        super(employee_id, name, department, salary, level);
        this.programmingLanguage = programmingLanguage;
        this.branch = branch;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getBranch() {
        return branch;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Programming Language: " + programmingLanguage);
    }

    
}