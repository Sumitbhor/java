import java.util.*;
import java.io.*;

// =============================
// Base class: Employee
// =============================
class Employee {
    protected int id;
    protected String name;
    protected double salary;
    protected String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }

    public void displayInfo() {
        System.out.println("-----------------------------------");
        System.out.println("Employee ID: " + id);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("Salary     : " + salary);
    }

    public double calculateBonus() {
        return salary * 0.05; // default 5%
    }

    public double computeFinalSalary() {
        return salary + calculateBonus();
    }

    public String toCSV() {
        return String.join(",", "Regular", String.valueOf(id), name, String.valueOf(salary), department, "0", "0", "0");
    }

    public static Employee fromCSV(String[] data) {
        int id = Integer.parseInt(data[1]);
        String name = data[2];
        double salary = Double.parseDouble(data[3]);
        String dept = data[4];
        return new Employee(id, name, salary, dept);
    }
}

// =============================
// Derived class: SalesEmployee
// =============================
class SalesEmployee extends Employee {
    private double sales;
    private double commissionRate;
    private double target;

    public SalesEmployee(int id, String name, double salary, String department, double sales, double commissionRate, double target) {
        super(id, name, salary, department);
        this.sales = sales;
        this.commissionRate = commissionRate;
        this.target = target;
    }

    public double getSales() { return sales; }
    public void setSales(double sales) { this.sales = sales; }

    @Override
    public double calculateBonus() {
        double baseBonus = (sales * commissionRate) + (salary * 0.02);
        if (sales >= target) {
            baseBonus += salary * 0.10; // incentive
        }
        return baseBonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Sales      : " + sales);
        System.out.println("Commission : " + (commissionRate * 100) + "%");
        System.out.println("Target     : " + target);
    }

    @Override
    public String toCSV() {
        return String.join(",", "Sales", String.valueOf(id), name, String.valueOf(salary), department, String.valueOf(sales),
                String.valueOf(commissionRate), String.valueOf(target));
    }

    public static SalesEmployee fromCSV(String[] data) {
        int id = Integer.parseInt(data[1]);
        String name = data[2];
        double salary = Double.parseDouble(data[3]);
        String dept = data[4];
        double sales = Double.parseDouble(data[5]);
        double rate = Double.parseDouble(data[6]);
        double target = Double.parseDouble(data[7]);
        return new SalesEmployee(id, name, salary, dept, sales, rate, target);
    }
}

// =============================
// Derived class: Manager
// =============================
class Manager extends Employee {
    private int teamSize;

    public Manager(int id, String name, double salary, String department, int teamSize) {
        super(id, name, salary, department);
        this.teamSize = teamSize;
    }

    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }

    @Override
    public double calculateBonus() {
        return salary * 0.1 + teamSize * 500;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Team Size  : " + teamSize);
    }

    @Override
    public String toCSV() {
        return String.join(",", "Manager", String.valueOf(id), name, String.valueOf(salary), department,
                String.valueOf(teamSize), "0", "0");
    }

    public static Manager fromCSV(String[] data) {
        int id = Integer.parseInt(data[1]);
        String name = data[2];
        double salary = Double.parseDouble(data[3]);
        String dept = data[4];
        int teamSize = Integer.parseInt(data[5]);
        return new Manager(id, name, salary, dept, teamSize);
    }
}

// =============================
// HR Management System
// =============================
class HRManagementSystem {
    private ArrayList<Employee> employees;
    private Scanner sc;
    private final String FILE_NAME = "employees.csv";

    public HRManagementSystem() {
        employees = new ArrayList<>();
        sc = new Scanner(System.in);
        loadFromFile();
    }

    // =============================
    // File Operations
    // =============================
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                pw.println(e.toCSV());
            }
        } catch (IOException ex) {
            System.out.println("Error saving to file: " + ex.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];
                switch (type) {
                    case "Regular":
                        employees.add(Employee.fromCSV(data));
                        break;
                    case "Sales":
                        employees.add(SalesEmployee.fromCSV(data));
                        break;
                    case "Manager":
                        employees.add(Manager.fromCSV(data));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // =============================
    // CRUD Operations
    // =============================
    public void addEmployee() {
        System.out.println("\n=== Add New Employee ===");
        System.out.print("Enter Employee Type (1. Regular, 2. Sales, 3. Manager): ");
        int type = sc.nextInt();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = null;

        switch (type) {
            case 1:
                emp = new Employee(id, name, salary, dept);
                break;
            case 2:
                System.out.print("Enter Total Sales: ");
                double sales = sc.nextDouble();
                System.out.print("Enter Commission Rate (e.g., 0.05 for 5%): ");
                double rate = sc.nextDouble();
                System.out.print("Enter Sales Target: ");
                double target = sc.nextDouble();
                emp = new SalesEmployee(id, name, salary, dept, sales, rate, target);
                break;
            case 3:
                System.out.print("Enter Team Size: ");
                int team = sc.nextInt();
                emp = new Manager(id, name, salary, dept, team);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        employees.add(emp);
        saveToFile();
        System.out.println("Employee added successfully!");
    }

    public void displayAll() {
        System.out.println("\n=== Employee List ===");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee e : employees) {
            e.displayInfo();
            System.out.println("Bonus: " + e.calculateBonus());
            System.out.println("Final Salary: " + e.computeFinalSalary());
            System.out.println("-----------------------------------");
        }
    }

    public void searchEmployee() {
        System.out.print("\nEnter Employee ID to search: ");
        int id = sc.nextInt();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.displayInfo();
                System.out.println("Bonus: " + e.calculateBonus());
                System.out.println("Final Salary: " + e.computeFinalSalary());
                found = true;   
                break;
            }
        }
        if (!found) System.out.println("Employee not found!");
    }

    public void deleteEmployee() {
        System.out.print("\nEnter Employee ID to delete: ");
        int id = sc.nextInt();
        Iterator<Employee> it = employees.iterator();
        boolean deleted = false;
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.getId() == id) {
                it.remove();
                deleted = true;
                System.out.println("Employee deleted successfully!");
                break;
            }
        }
        if (!deleted) System.out.println("Employee not found!");
        saveToFile();
    }

    public void updateEmployee() {
        System.out.print("\nEnter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                System.out.print("Enter new department: ");
                String dept = sc.nextLine();
                System.out.print("Enter new salary: ");
                double sal = sc.nextDouble();
                e.setName(name);
                e.setDepartment(dept);
                e.setSalary(sal);
                System.out.println("Employee updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Employee not found!");
        saveToFile();
    }

    public void displayByDepartment() {
        sc.nextLine();
        System.out.print("Enter department name: ");
        String dept = sc.nextLine();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(dept)) {
                e.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No employees found in this department.");
    }

    // =============================
    // Menu
    // =============================
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n======= HR MANAGEMENT SYSTEM =======");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Update Employee Details");
            System.out.println("5. Delete Employee");
            System.out.println("6. Display Employees by Department");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: displayAll(); break;
                case 3: searchEmployee(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6: displayByDepartment(); break;
                case 7: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
    }
}

// =============================
// Main Class
// =============================
public class HRMain {
    public static void main(String[] args) {
        HRManagementSystem hr = new HRManagementSystem();
        hr.showMenu();
    }
}
