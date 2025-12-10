import java.util.ArrayList;
import java.util.Iterator;
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

    public List<List<ConnectionTreeNode>> printNodes(Employee target) {
        List<List<ConnectionTreeNode>> paths = new ArrayList<>();
        print(root, 0, paths);
        return paths;
    }   

    private void print(ConnectionTreeNode node, int depth, List<List<ConnectionTreeNode>> paths ) {
        if (node != null){
            List<ConnectionTreeNode> path = new ArrayList<>();
            
            ConnectionTreeNode currentParent = node.getParentNode();
            while (currentParent != null) {
                path.add(currentParent);
                currentParent = currentParent.getParentNode();
            }
            if (!paths.contains(path)){
                paths.add(path);
            }

            for (ConnectionTreeNode e : node.getConnectionTreeNodes()) {
                print(e, depth + 1, paths);
            }
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
                ConnectionTreeNode newNode = new ConnectionTreeNode(r.connectsWith(currentEmp) , r.getWeight());
                if (r.hasContainedEmployee(currentEmp) && !blockList.contains(newNode.getEmployeeNode())) {
                    newNode.setParentEmployeeNode(node);
                    node.addConnection(newNode);
                    blockList.add(node.getEmployeeNode());
                    add(newNode, depth + 1, relationships);
                }
            }
        } 
        
        // if (node.getConnectionTreeNodes().size() == 0) {
        //     System.out.println("Finished");
        // }  
    }
}
