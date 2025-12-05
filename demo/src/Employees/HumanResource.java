package Employees;
import Employees.Employee;

public class HumanResource extends Employee {
    private String[] recruitmentFocus = new String[10];
    private String[] platforms = new String[10]; 


    public HumanResource(String employee_id, String name, String department, float salary, String level, String[] recruitmentFocus, String[] platforms) {
        super(employee_id, name, department, salary, level);
        this.platforms = platforms;
        this.recruitmentFocus = recruitmentFocus;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Recruitment Focus: ");
        for (String focus : recruitmentFocus) {
            System.out.println(focus);
        }

        System.out.println("Platforms used to hire: ");
        for (String pf : platforms) {
            System.out.println(pf);
        }
    }

}
