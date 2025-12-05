package Relationships;

import Employees.Employee;

public class Relation {
    private String connectionName;
    private int weight;
    private Employee[] connection = new Employee[2];

    public Relation(Employee emp1, Employee emp2) {
        this.connectionName = "connect";
        this.weight = 10;
        Employee[] c = {emp1, emp2};
        this.connection = c;
    }

    public void setName(String name) {
        connectionName = name;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public String getName() {
        return connectionName;
    }

    public int getWeight() {
        return weight;
    }
    public Employee[] getConnectionBetweenEmployee() {
        return connection;
    }

}
