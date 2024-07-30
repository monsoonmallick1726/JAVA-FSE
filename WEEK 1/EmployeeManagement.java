class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public EmployeeManagementSystem() {
        employees = new Employee[DEFAULT_CAPACITY];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size == employees.length) {
            // If array is full, create a new array with double capacity
            Employee[] newEmployees = new Employee[employees.length * 2];
            System.arraycopy(employees, 0, newEmployees, 0, employees.length);
            employees = newEmployees;
        }
        employees[size++] = employee;
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                // Move the last employee to this position
                employees[i] = employees[size - 1];
                employees[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }
}
public class EmployeeManagement {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();

        // Add employees
        ems.addEmployee(new Employee(1, "John Doe", "Developer", 75000));
        ems.addEmployee(new Employee(2, "Jane Smith", "Manager", 95000));
        ems.addEmployee(new Employee(3, "Bob Johnson", "Designer", 65000));

        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee
        Employee foundEmployee = ems.searchEmployee(2);
        System.out.println("\nFound Employee: " + foundEmployee);

        // Delete an employee
        boolean deleted = ems.deleteEmployee(1);
        System.out.println("\nEmployee deleted: " + deleted);

        System.out.println("\nEmployees after deletion:");
        ems.traverseEmployees();

        System.out.println("\nTotal employees: " + ems.getSize());
    }
}
