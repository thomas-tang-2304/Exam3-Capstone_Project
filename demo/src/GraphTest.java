

import java.util.ArrayList;
import java.util.List;

import Employees.Employee;

public class GraphTest {
    public static boolean assertTrue(List<ConnectionTreeNode> fastestRoute, int totalChecker, Employee initial, Employee target) {
        List<String> fastestRouteChecker = new ArrayList<>();
        int total = 0;
        // from EGN001 to HR012
        if (initial.getEmployeeId() == "EGN001" && target.getEmployeeId() == "HR012") {
            fastestRouteChecker.add("EGN001");
            fastestRouteChecker.add("HR012");
            total = 1;
        } else if (initial.getEmployeeId() == "HR012" && target.getEmployeeId() == "SE032") {
            total = 999999999;
        } else if (initial.getEmployeeId() == "EGN001" && target.getEmployeeId() == "SE1342") {
            total = 999999999;
        } else if (initial.getEmployeeId() == "SE001" && target.getEmployeeId() == "M001") {
            fastestRouteChecker.add("SE001");
            fastestRouteChecker.add("SE002");
            fastestRouteChecker.add("M001");
            total = 2;
        }
        
        for (int i = 0; i < fastestRoute.size(); i++) {
            if (!fastestRoute.get(i).getEmployeeNode().getEmployeeId().equals(fastestRouteChecker.get(i))) {
                return false;
            }
        }
        return totalChecker == total ? true : false;
    }

}
