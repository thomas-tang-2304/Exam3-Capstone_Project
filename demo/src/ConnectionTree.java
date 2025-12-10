import java.util.ArrayList;
import java.util.List;

import Employees.Employee;
import Relationships.Relation;

public class ConnectionTree {
    private ConnectionTreeNode root;

    public ConnectionTree() {
        this.root = null;
    }

    public ConnectionTree(ConnectionTreeNode root) {
        this.root = root;
    }

    public List<List<ConnectionTreeNode>> relativeCollectionList(Employee target) {
        List<List<ConnectionTreeNode>> paths = new ArrayList<>();
        relativeCollectionListRecursion(root, 0, paths);
        return paths;
    }   

    private void relativeCollectionListRecursion(ConnectionTreeNode node, int depth, List<List<ConnectionTreeNode>> paths) {
        List<ConnectionTreeNode> path = new ArrayList<>();
        path.add(node);
        ConnectionTreeNode currentParent = node.getParentNode();
        while (currentParent != null) {
            path.add(currentParent);
            currentParent = currentParent.getParentNode();
        }
        if (!paths.contains(path)){
            paths.add(path);
        }

        for (ConnectionTreeNode e : node.getConnectionTreeNodes()) {
            relativeCollectionListRecursion(e, depth + 1, paths);
        }
        
    }

    public void addConnectionMap(List<Relation> relationships) {
        add(root, 0, relationships);
    }   

    private void add(ConnectionTreeNode node, int depth, List<Relation> relationships) {
        List<Employee> blockList = new ArrayList<>();
        if (node != null ){
            ConnectionTreeNode currentParent = node.getParentNode();
            while (currentParent != null) {
                blockList.add(currentParent.getEmployeeNode());
                currentParent = currentParent.getParentNode();
            }
           
            for (Relation r : relationships) {
                Employee currentEmp = node.getEmployeeNode();
                if (r.hasContainedEmployee(currentEmp)){
                    
                    ConnectionTreeNode newNode = new ConnectionTreeNode(r.connectsWith(currentEmp) , r.getWeight());
                    if (!blockList.contains(newNode.getEmployeeNode())) {
                        newNode.setParentEmployeeNode(node);
                        node.addConnection(newNode);
                        blockList.add(node.getEmployeeNode());
                        add(newNode, depth + 1, relationships);
                    }
                }
            }
        } 
     

    }
}
