import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class EmployeeManagement {

    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static final String FILE_NAME = "employees.txt";

    // Add employee
    static void addEmployee() {

        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative.");
            }

            employees.add(new Employee(id, name, department, salary));

            System.out.println("Employee added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers correctly.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display employees
    static void displayEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\nEmployee Details");
        System.out.println("---------------------------------------------");

        for (Employee emp : employees) {

            System.out.println("ID         : " + emp.id);
            System.out.println("Name       : " + emp.name);
            System.out.println("Department : " + emp.department);
            System.out.println("Salary     : " + emp.salary);

            System.out.println("---------------------------------------------");
        }
    }

    // Save employees to file
    static void saveEmployees() {

        try {

            FileWriter writer = new FileWriter(FILE_NAME);

            for (Employee emp : employees) {
                writer.write(emp.toString() + "\n");
            }

            writer.close();

            System.out.println("Employee data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    // Load employees from file
    static void loadEmployees() {

        employees.clear();

        try {

            FileReader reader = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(reader);

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String department = data[2];
                double salary = Double.parseDouble(data[3]);

                employees.add(
                    new Employee(id, name, department, salary)
                );
            }

            br.close();

            System.out.println("Employee data loaded successfully.");

        } catch (FileNotFoundException e) {

            System.out.println("Employee file not found.");

        } catch (IOException e) {

            System.out.println("Error while reading file: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Invalid data found in file.");
        }
    }

    // Main menu
    public static void main(String[] args) {

        loadEmployees();

        while (true) {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Save Employees");
            System.out.println("4. Load Employees");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    addEmployee();
                    break;

                case "2":
                    displayEmployees();
                    break;

                case "3":
                    saveEmployees();
                    break;

                case "4":
                    loadEmployees();
                    break;

                case "5":
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}