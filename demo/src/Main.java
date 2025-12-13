import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Employees.Employee;
import Employees.Engineer;
import Employees.HumanResource;
import Employees.Manager;
import Employees.SoftwareEngineer;
import Relationships.DirectConnection;
import Relationships.ExtendedNetwork;
import Relationships.Relation;
import Relationships.SecondConnection;

public class Main {
    public static Employee inputInitialEmployee(Scanner scanner, List<Employee> employees) {
        try {
            int i = 0;
            System.out.println("Enter the initial Employee: ");
            for (Employee e : employees) {
                System.out.println(i + ": ======================================================================================");
                e.getDetails();
                i++;
            }
            int index = scanner.nextInt();
            
            if (index < i && index >= 0) {

                return employees.get(index);
            } else {
                System.out.println("Invalid option");
                return inputInitialEmployee(new Scanner(System.in), employees);
            }
        } catch (InputMismatchException err) {
            System.out.println("Invalid Input");
            return inputInitialEmployee(new Scanner(System.in), employees);
        }
    }

    public static Employee inputTargetEmployee(Scanner scanner, List<Employee> employees, Employee initial) {
        try {

            int i = 0;
            System.out.println("Enter the target Employee: ");
            for (Employee e : employees) {
                System.out.println(i + ": ======================================================================================");
                e.getDetails();
                i++;
            }
            int index = scanner.nextInt();
    
            
            if (index < i && index >= 0) {
                scanner.close();
                return employees.get(index);
                
            } else {
                System.out.println("Invalid option");
                return inputTargetEmployee(new Scanner(System.in), employees, initial);
            }
        } catch (InputMismatchException err) {
            System.out.println("Invalid Input");
            return inputTargetEmployee(new Scanner(System.in), employees, initial);
        }
    }
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        Manager mgr = new Manager("M001", "Jack Sparrow", "Sales", 95000.0f, "Senior", 10);
        SoftwareEngineer dev = new SoftwareEngineer("SE001", "Home Lander", "Development", 85000.0f, "Intermediate", "Java", "Backend Developer");
        SoftwareEngineer dev2 = new SoftwareEngineer("SE002", "Steve Jobs", "Production", 999999.0f, "CEO", "Swift", "Full Stack Developer");
        SoftwareEngineer dev3 = new SoftwareEngineer("SE032", "Calmar Justin", "Devops", 123000.0f, "Senior", "Java, C#", "Full Stack Developer");
        SoftwareEngineer dev4 = new SoftwareEngineer("SE1342", "Larria Pollar Bear", "White hat hacker", 300000.0f, "Senior", "PHP, HTML, CSS", "Full Stack Developer");
        String[] currentProject = {"81 Lanmark", "Wiz Khalif"};
        Engineer engineer = new Engineer("EGN001", "Stephen Brownie", "Construction", 100200.0f, "Chief", currentProject);

        String[] platforms = {"Facebook", "LinkedIn", "Techify"};
        String[] focus = {"Technology", "Assembly", "AC Technician"};
        HumanResource hr = new HumanResource("HR012", "Elynna.Y", "Human Resource for Tech", 60000, "Fresher", focus, platforms);

        List<Employee> employees = new ArrayList<>();
        employees.add(mgr);
        employees.add(dev);
        employees.add(dev3);
        employees.add(dev4);
        employees.add(hr);
        employees.add(engineer);


        // Adding connection relationships 
        List<Relation> relationships = new ArrayList<Relation>();

        // DirectConnection has weight = 1, directly connection class
        // SecondConnection has weight = 2
        // ExtendedNetwork has weight = 3, largest weight 
        // relationships.add(new ExtendedNetwork("Alumni", dev4, engineer));
        relationships.add(new DirectConnection("co-workers", mgr, dev2));
        relationships.add(new DirectConnection("friends", dev, dev2));
        relationships.add(new ExtendedNetwork("connected via LinkedIn", engineer, dev));
        relationships.add(new DirectConnection("Alumni", hr, mgr));
        relationships.add(new DirectConnection("Alumni", hr, dev));
        relationships.add(new DirectConnection("Alumni", hr, dev2));
        relationships.add(new DirectConnection("Alumni", engineer, hr));
        relationships.add(new SecondConnection("referrals", mgr, engineer));
        relationships.add(new SecondConnection("Facebook", dev4, dev3));


        // create initial node, this is the starting point where travels throughout every single posible Employees.
        ConnectionTreeNode initial = new ConnectionTreeNode(inputInitialEmployee(scanner,employees), 0);
        employees.remove(initial.getEmployeeNode());

        // Determine the target node as the destination
        Employee target = inputTargetEmployee(scanner, employees, hr);
      
        // Implementing the first node of tree, root is initial node
        ConnectionTree tree = new ConnectionTree(initial);

        // Populating relationships through the relationships List predefined above, resulted a tree map /
        tree.addConnectionMap(relationships);

       

        // fastestConnection is the list which stores a employees path associated with connection weights
        List<ConnectionTreeNode> fastestConnection = new ArrayList<>();
        int min = 999999999; // set min value to infinity

        // Finding the fastest route to destination
        for (List<ConnectionTreeNode> lcn : tree.relativeCollectionList(target)) {
            int weightCount = 0;
            for (int i = lcn.size() - 1; i >= 0; i--) {
                ConnectionTreeNode cn = lcn.get(i);
                System.out.print(cn.getEmployeeNode().getEmployeeId() );
                if (i > 0) {
                    System.out.print(" -> ");
                } else {
                    System.out.print(" \t ");
                }
                weightCount+=cn.getConnectioWeight();
            }
            if (min > weightCount && weightCount > 0 && target.getEmployeeId().equals(lcn.getFirst().getEmployeeNode().getEmployeeId())) {
                min = weightCount;
                fastestConnection = lcn;
            } else if (min == weightCount && lcn.size() < fastestConnection.size()) {
                fastestConnection = lcn;
            }
            System.out.println("Total Weight: " + weightCount);
        }

        System.out.println("===============================================================================");
        
        if (fastestConnection.size() == 0) {
            // In case we can't look for the connection
            System.out.println("No connection found between " + initial.getEmployeeNode().getEmployeeId() + " and " + target.getEmployeeId());
        }else {
            
            // Print out fastest route path and total weight
            System.out.print("Fastest route: ");
            for (int i = fastestConnection.size() - 1; i >= 0; i--) {
                ConnectionTreeNode cn  = fastestConnection.get(i);
                System.out.print(cn.getEmployeeNode().getEmployeeId()+ " - name: " + cn.getEmployeeNode().getName());
                if (i > 0) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            System.out.println("Min total Weight: " + min);
            
        }
        if (target.getEmployeeId() == "HR012" && initial.getEmployeeNode().getEmployeeId() == "EGN001") {
            // Unit test at scenario of EGN001 connect with target of HR012, result in list of possible all routes map
            System.out.println(GraphTest.assertTrue(fastestConnection.reversed(), min, initial.getEmployeeNode(), target) ? "== PASSED ==" : "-- FAILED --");
        } 
        if (target.getEmployeeId() == "SE032" && initial.getEmployeeNode().getEmployeeId() == "HR012") {
            System.out.println(GraphTest.assertTrue(fastestConnection.reversed(), min, initial.getEmployeeNode(), target) ? "== PASSED ==" : "-- FAILED --");
        }
        if (target.getEmployeeId() == "SE1342" && initial.getEmployeeNode().getEmployeeId() == "EGN001") {
            System.out.println(GraphTest.assertTrue(fastestConnection.reversed(), min, initial.getEmployeeNode(), target) ? "== PASSED ==" : "-- FAILED --");
        } 
        if (target.getEmployeeId() == "M001" && initial.getEmployeeNode().getEmployeeId() == "SE001") {
            System.out.println(GraphTest.assertTrue(fastestConnection.reversed(), min, initial.getEmployeeNode(), target) ? "== PASSED ==" : "-- FAILED --");
        } 
        scanner.close();
    }
}
