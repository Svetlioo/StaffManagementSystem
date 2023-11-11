package com.system.Services;

import com.system.Exceptions.*;
import com.system.Models.Employee;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class InputValidator {
    // Injecting the scanner, so that I can try-with-resources open it in the main and not worry about it.
    private final Scanner scanner;
    private final Map<Long, Employee> employeeMap;

    public InputValidator(Scanner scanner, Map<Long, Employee> employeeMap) {
        this.scanner = scanner;
        this.employeeMap = employeeMap;
    }

    public long getValidNewID() {
        long input;
        while (true) {
            try {
                System.out.print("Enter Employee's ID: ");
                input = Long.parseLong(scanner.nextLine());
                if (employeeMap.containsKey(input)) {
                    throw new DuplicateIDException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (DuplicateIDException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public long getValidID() {
        long input;
        while (true) {
            try {
                System.out.print("Enter Employee's ID: ");
                // This will throw NumberFormatException if the user inputs something different from a number.
                input = Long.parseLong(scanner.nextLine());
                // This will throw InvalidIDException (custom exception) if the user inputs an Employee ID that does not exist.
                if (!employeeMap.containsKey(input)) {
                    throw new InvalidIDException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public String getValidString(String prompt) {
        String input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextLine().trim();
                // Only characters and whitespace allowed in the name, role and department. Ensuring it using regex and custom exception message.
                if (input.isEmpty() || !input.matches("[A-Za-z\\s]+")) throw new InvalidStringException();
                break;
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            }

        }
        return input;
    }

    public String getValidName() {
        String input;
        while (true) {
            try {
                System.out.print("Enter Employee's Name: ");
                input = scanner.nextLine().trim();
                // Only characters and whitespace allowed in the name, role and department. Ensuring it using regex and custom exception message.
                if (input.isEmpty() || !input.matches("[A-Za-z\\s]+")) throw new InvalidStringException();
                boolean nameExists = false;
                for (Employee employee : employeeMap.values()) {
                    if (employee.getName().equalsIgnoreCase(input)) {
                        nameExists = true;
                        // Doing this, so I can get it with the right capitalization
                        input = employee.getName();
                        break;
                    }
                }
                if (!nameExists) throw new InvalidNameException();
                break;
            } catch (InvalidStringException | InvalidNameException e) {
                System.out.println(e.getMessage());
            }

        }
        return input;
    }

    public String getValidDepartment() {
        String input;
        while (true) {
            try {
                System.out.print("Enter Employee's Department: ");
                input = scanner.nextLine().trim();
                // Only characters and whitespace allowed in the name, role and department. Ensuring it using regex and custom exception message.
                if (input.isEmpty() || !input.matches("[A-Za-z\\s]+")) throw new InvalidStringException();
                boolean departmentExists = false;
                for (Employee employee : employeeMap.values()) {
                    if (employee.getDepartment().equalsIgnoreCase(input)) {
                        departmentExists = true;
                        // Doing this, so I can get it with the right capitalization
                        input = employee.getDepartment();
                        break;
                    }
                }
                if (!departmentExists) throw new InvalidDepartmentException();
                break;
            } catch (InvalidStringException | InvalidDepartmentException e) {
                System.out.println(e.getMessage());
            }

        }
        return input;
    }

    public double getValidSalary() {
        double input;
        while (true) {
            try {
                System.out.print("Enter Employee's Salary: ");
                // This will throw NumberFormatException if the user inputs something different from a number.
                input = Double.parseDouble(scanner.nextLine());
                // This will throw InvalidIDException (custom exception) if the user inputs an Employee ID that does not exist.
                if (input < 780) {
                    throw new BelowMinimumSalaryException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            } catch (BelowMinimumSalaryException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public Employee getValidEmployee(long id) {
        String name = getValidString("Enter Employee's Name: ");
        Date date = new Date();
        String department = getValidString("Enter Employee's Department: ");
        String role = getValidString("Enter Employee's Role: ");
        double salary = getValidSalary();
        return new Employee(id, name, date, department, role, salary);
    }

    public Employee getValidNewEmployee() {
        long id = getValidNewID();
        String name = getValidString("Enter Employee's Name: ");
        Date date = new Date();
        String department = getValidString("Enter Employee's Department: ");
        String role = getValidString("Enter Employee's Role: ");
        double salary = getValidSalary();
        return new Employee(id, name, date, department, role, salary);
    }
}
