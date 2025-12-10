import java.util.ArrayList;
import java.util.List;

import Employees.Employee;
import Employees.Engineer;
import Employees.HumanResource;
import Employees.Manager;
import Employees.SoftwareEngineer;
import Relationships.DirectConnection;
import Relationships.ExtendedNetwork;
import Relationships.Relation;
import Relationships.SecondConnection;

public class ConnectionDriver {
    public static void main(String[] args) throws Exception {

        Manager mgr = new Manager("M001", "Jack Sparrow", "Sales", 95000.0f, "Senior", 10);
        SoftwareEngineer dev = new SoftwareEngineer("SE001", "Home Lander", "Development", 85000.0f, "Intermediate", "Java", "Backend Developer");
        SoftwareEngineer dev2 = new SoftwareEngineer("DO002", "Steve Jobs", "Production", 999999.0f, "CEO", "Swift", "Full Stack Developer");
        String[] currentProject = {"81 Lanmark", "Wiz Khalif"};
        Engineer engineer = new Engineer("EGN001", "Stephen Brownie", "Construction", 100200.0f, "Chief", currentProject);

        String[] platforms = {"Facebook", "LinkedIn", "Techify"};
        String[] focus = {"Technology", "Assembly", "AC Technician"};
        HumanResource hr = new HumanResource("HR012", "Elynna.Y", "Human Resource for Tech", 60000, "Fresher", focus, platforms);


        // mgr.getDetails();
        // dev.getDetails();
        // dev2.getDetails();
        // engineer.getDetails();
        // hr.getDetails();

        List<Relation> relationships = new ArrayList<Relation>();

        relationships.add(new DirectConnection("co-workers", mgr, dev2));
        relationships.add(new DirectConnection("friends", dev, dev2));
        relationships.add(new ExtendedNetwork("connected via LinkedIn", engineer, dev));
        relationships.add(new DirectConnection("Alumni", hr, mgr));
        relationships.add(new DirectConnection("Alumni", hr, dev));
        relationships.add(new DirectConnection("Alumni", hr, dev2));
        relationships.add(new DirectConnection("Alumni", engineer, hr));
        relationships.add(new SecondConnection("referrals", engineer, mgr));


        ConnectionTreeNode initial = new ConnectionTreeNode(engineer, 0);
        Employee target = mgr;
      
        ConnectionTree tree = new ConnectionTree(initial);
        tree.addConnectionMap(relationships);

        List<ConnectionTreeNode> fastestConnection = new ArrayList<>();
        int min = 999999999;
        for (List<ConnectionTreeNode> lcn : tree.printNodes(target)) {
            int weightCount = 0;
            for (ConnectionTreeNode cn : lcn) {
                System.out.print(cn.getEmployeeNode().getEmployeeId() + ", ");
                weightCount+=cn.getConnectioWeight();
            }
            if (min > weightCount && weightCount > 0 && target.getEmployeeId().equals(lcn.getFirst().getEmployeeNode().getEmployeeId())) {
                min = weightCount;
                fastestConnection = lcn;
            }
            System.out.println(weightCount);
        }
        System.out.print("Fastest route: ");
        for (int i = fastestConnection.size() - 1; i >= 0; i--) {
            ConnectionTreeNode cn  = fastestConnection.get(i);
            System.out.print(cn.getEmployeeNode().getEmployeeId()+ " - name: " + cn.getEmployeeNode().getName() + ", ");
        }
        System.out.println();



        // for (Relation r : relationships) {
        //     System.out.println("This relationship is between id " + r.getConnectionBetweenEmployee()[0].getEmployeeId() + " and " + r.getConnectionBetweenEmployee()[1].getEmployeeId());
        //     System.out.println("Relationship name: " + r.getName() + ", weight: " + r.getWeight()) ;
        // }
    }
}
