package Relationships;

import Employees.Employee;

public class SecondConnection extends Relation {
    public SecondConnection(String name, Employee emp1, Employee emp2) {
        super(emp1, emp2);
        super.setName(name);
        super.setWeight(2);
    }
}
