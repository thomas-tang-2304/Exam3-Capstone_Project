package Employees;

public class Engineer extends Employee{
    private String[] currentProjects = new String[10];

    public Engineer(String employee_id, String name, String department, float salary, String level, String[] currentProjects) {
        super(employee_id, name, department, salary, level);
        this.currentProjects = currentProjects;
    }

    public String getProjectNumber(int index) {
        if (index >= currentProjects.length) {
            System.out.println("NO project found");
            return "";
        } 
        return currentProjects[index];
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Current Projects: " + currentProjects.length);
        for (String proj : currentProjects) {
            System.out.println(proj);
        }
    }
}
