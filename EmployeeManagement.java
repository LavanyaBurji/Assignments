import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {
    private static final String URL = "jdbc:postgresql://localhost:5432/employee_db"; // Change if needed
    private static final String USER = "your_username";  // Replace with your PostgreSQL username
    private static final String PASSWORD = "your_password"; // Replace with your PostgreSQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n-----------------------");
            System.out.println("   1. Create Employee");
            System.out.println("   2. Display Employees");
            System.out.println("   3. Appraisal (Update Salary)");
            System.out.println("   4. Search Employees (By Designation)");
            System.out.println("   5. Remove Employee");
            System.out.println("   6. Exit");
            System.out.println("-----------------------");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createEmployee(scanner);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    updateSalary(scanner);
                    break;
                case 4:
                    searchByDesignation(scanner);
                    break;
                case 5:
                    removeEmployee(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Function to create a new employee
    private static void createEmployee(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        String sql = "INSERT INTO employees (name, age, salary, designation, department) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, salary);
            pstmt.setString(4, designation);
            pstmt.setString(5, department);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to display all employees
    private static void displayEmployees() {
        String sql = "SELECT * FROM employees";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nID | Name | Age | Salary | Designation | Department");
            System.out.println("--------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %.2f | %s | %s\n",
                        rs.getInt("eid"), rs.getString("name"), rs.getInt("age"),
                        rs.getDouble("salary"), rs.getString("designation"),
                        rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to update employee salary
    private static void updateSalary(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Salary Change (e.g., +5000 or -2000): ");
        double change = scanner.nextDouble();

        String sql = "UPDATE employees SET salary = salary + ? WHERE eid = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, change);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to search employees by designation
    private static void searchByDesignation(Scanner scanner) {
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();

        String sql = "SELECT * FROM employees WHERE designation = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, designation);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nID | Name | Age | Salary | Designation | Department");
            System.out.println("--------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %.2f | %s | %s\n",
                        rs.getInt("eid"), rs.getString("name"), rs.getInt("age"),
                        rs.getDouble("salary"), rs.getString("designation"),
                        rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to remove an employee
    private static void removeEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE eid = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
