import java.util.ArrayList;
import java.util.List;

import Employees.Employee;

public class ConnectionTreeNode {
    private ConnectionTreeNode parent;
    private Employee employeeNode;
    private List<ConnectionTreeNode> connection;
    private int connectionWeight;

    public ConnectionTreeNode() {
        this.parent = null;
        this.employeeNode = null;
        this.connection = new ArrayList<ConnectionTreeNode>();
        this.connectionWeight = 0;
    }

    public ConnectionTreeNode(Employee employeeNode, int weight) {
        this.employeeNode = employeeNode;
        this.connection = new ArrayList<ConnectionTreeNode>();
        this.connectionWeight = weight;
    }

    public List<ConnectionTreeNode> getConnectionTreeNodes() {
        return connection;
    }

    public Employee getEmployeeNode() {
        return employeeNode;
    }
    public int getConnectioWeight() {
        return connectionWeight;
    }

    public Employee getParentEmployee() {
        return parent.getEmployeeNode();
    }

    public ConnectionTreeNode getParentNode() {
        return parent;
    }

    public void setParentEmployeeNode(ConnectionTreeNode eN) {
        parent = eN;
    }


    public void addConnection(ConnectionTreeNode e) {
        if (connection.contains(e)) {
            System.out.println(e.getEmployeeNode().getName() + " exists");
        }else {

            connection.add(e);
        }
    }
}

