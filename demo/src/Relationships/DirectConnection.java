package Relationships;

import Employees.Employee;

public class DirectConnection extends Relation {
    public DirectConnection(String name, Employee emp1, Employee emp2) {
        super(emp1, emp2);
        super.setName(name);
        super.setWeight(1);
        
    }
}
