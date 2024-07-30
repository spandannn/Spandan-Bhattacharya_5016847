public class EmployeeManagementTest {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        // Add employees
        ems.addEmployee(new Employee("E001", "Alice", "Manager", 75000));
        ems.addEmployee(new Employee("E002", "Bob", "Developer", 60000));
        ems.addEmployee(new Employee("E003", "Charlie", "Designer", 55000));

        // Traverse employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee
        Employee employee = ems.searchEmployee("E002");
        if (employee != null) {
            System.out.println("Found Employee: " + employee.getName());
        } else {
            System.out.println("Employee not found.");
        }

        // Delete an employee
        ems.deleteEmployee("E002");
        System.out.println("After Deletion:");
        ems.traverseEmployees();
    }
}
