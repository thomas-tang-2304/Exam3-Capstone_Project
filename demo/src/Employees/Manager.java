package Employees;
import Employees.Employee;


public class Manager extends Employee {
    private int teamSize;

    public Manager(String employee_id, String name, String department, float salary, String level, int teamSize) {
        super(employee_id, name, department, salary, level);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Team Size: " + teamSize);
    }
}