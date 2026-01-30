import java.util.Scanner;

class Employee {

    int empId;
    String empName;
    String department;
    float basicSalary;

    Employee(int empId, String empName, String department, float basicSalary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    void display() {
        System.out.println("----------------------");
        System.out.println("ID : " + empId);
        System.out.println("Name : " + empName);
        System.out.println("Department : " + department);
        System.out.println("Salary : " + basicSalary);
    }
}


class EmployeeManagement {

    Employee[] emp;        
    int count = 0;         
    Scanner sc = new Scanner(System.in);

    void createArray(int size) {
        emp = new Employee[size];
    }

    
    void menu() {
        System.out.println("\n===== Employee Management =====");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search By ID");
        System.out.println("4. Exit");
    }

    
    int getChoice() {
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }

   
    void addEmployee() {

        if (count >= emp.length) {
            System.out.println("Employee Array is Full!");
            return;
        }

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Department: ");
        String dept = sc.next();

        System.out.print("Enter Salary: ");
        float sal = sc.nextFloat();

        emp[count] = new Employee(id, name, dept, sal);
        count++;

        System.out.println("Employee Added Successfully!");
    }

    // Display all
    void displayAll() {

        if (count == 0) {
            System.out.println("No Employees Found!");
            return;
        }

        for (int i = 0; i < count; i++) {
            emp[i].display();
        }
    }

    // Search
    void searchById() {

        System.out.print("Enter ID to Search: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (emp[i].empId == id) {
                emp[i].display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee Not Found!");
        }
    }
}



public class Operations {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeManagement mgr = new EmployeeManagement();

        System.out.print("Enter Total Employees: ");
        int n = sc.nextInt();

        mgr.createArray(n);

        while (true) {

            mgr.menu();

            int choice = mgr.getChoice();

            switch (choice) {

                case 1:
                    mgr.addEmployee();
                    break;

                case 2:
                    mgr.displayAll();
                    break;

                case 3:
                    mgr.searchById();
                    break;

                case 4:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
