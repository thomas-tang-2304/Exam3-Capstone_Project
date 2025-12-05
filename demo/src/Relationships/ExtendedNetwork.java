package Relationships;

import Employees.Employee;

public class ExtendedNetwork extends Relation {
    public ExtendedNetwork(String name, Employee emp1, Employee emp2) {
        super(emp1, emp2);
        super.setName(name);
        super.setWeight(3);
        
    }
}
