package com.system.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.system.Exceptions.InvalidIDException;
import com.system.Models.Employee;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.system.Models.MenuDisplay.Press0ToExit;
import static com.system.Models.MenuDisplay.exitMessage;

public class StaffService implements Service<Employee>, JsonSerializable<Employee> {
    private final Map<Long, Employee> employeeMap;
    private final InputValidator inputValidator;

    // Injecting the input validator and the scanner in the input validator.
    public StaffService(Scanner scanner) {
        this.employeeMap = loadFromJson();
        this.inputValidator = new InputValidator(scanner, this.employeeMap);
    }

    @Override
    public void add(Employee employee) {
        employeeMap.putIfAbsent(employee.getId(), employee);
        System.out.printf("Successfully added employee %s with id %d%n", employee.getName(), employee.getId());
    }

    @Override
    public void edit(long id, Employee employee) {
        employeeMap.replace(id, employee);
        System.out.printf("Successfully edited employee %s with id %d%n", employee.getName(), employee.getId());
    }

    @Override
    public void searchById(long id) {
        // This will be validated, so that check is pointless.
        // However, I leave it in case somebody in the future decides to change something in the validation in the user input.
        try {
            if (employeeMap.containsKey(id)) {
                System.out.println(employeeMap.get(id).toString());
            } else {
                throw new InvalidIDException();
            }
        } catch (InvalidIDException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void searchByName(String name) {
        for (Employee employee : employeeMap.values()) {
            if (employee.getName().equals(name)) {
                System.out.println(employee);
            }
        }
    }

    @Override
    public void searchByDepartment(String department) {
        for (Employee employee : employeeMap.values()) {
            if (employee.getDepartment().equals(department)) {
                System.out.println(employee);
            }
        }
    }

    @Override
    public void listAll() {
        if (employeeMap.isEmpty()) {
            System.out.println("There are no employees.");
        }
        for (Employee employee : employeeMap.values()) {
            System.out.println(employee);
        }
    }

    @Override
    public void delete(long id) {
        employeeMap.remove(id);
    }


    public void useCommand(String command) {
        switch (command) {
            case "1":
                this.add(inputValidator.getValidNewEmployee());
                break;
            case "2":
                long id = inputValidator.getValidID();
                System.out.println("Enter the new details: ");
                Employee employeeNewDetails = inputValidator.getValidEmployee(id);
                this.edit(id, employeeNewDetails);
                break;
            case "3":
                this.listAll();
                Press0ToExit();
                break;
            case "4":
                this.searchById(inputValidator.getValidID());
                Press0ToExit();
                break;
            case "5":
                this.searchByName(inputValidator.getValidName());
                Press0ToExit();
                break;
            case "6":
                this.searchByDepartment(inputValidator.getValidDepartment());
                Press0ToExit();
                break;
            case "7":
                this.delete(inputValidator.getValidID());
                break;
            case "8":
                this.saveToJson(employeeMap);
                exitMessage();
                System.exit(0);
            default:
                System.out.println("Invalid command!");
        }
    }

    @Override
    public Map<Long, Employee> loadFromJson() {
        File file = new File("src/main/resources/employees.json");
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            if (file.exists()) {
                Employee[] employees = gson.fromJson(reader, Employee[].class);
                Map<Long, Employee> itemMap = new HashMap<>();
                for (Employee employee : employees) {
                    // The equals here checks only if the id and the class are the same.
                    boolean itemExists = itemMap.values().stream().anyMatch(existingItem -> existingItem.equals(employee));
                    if (!itemExists) {
                        long itemId = employee.getId();
                        itemMap.put(itemId, employee);
                    } else {
                        // This will never be reached, as I will validate the data from the user input to ensure no matching ids.
                        // However, I am leaving it, in case someone touches the file manually.
                        System.out.println("Employee with the same id already exists, skipping: " + employee);
                    }
                }
                return itemMap;
            } else {
                return new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveToJson(Map<Long, Employee> map) {
        File file = new File("src/main/resources/employees.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(file)) {
            // Doing this "new Employee[0]" to convert the collection to an array of employee objects
            Employee[] items = map.values().toArray(new Employee[0]);
            gson.toJson(items, writer);
            System.out.println("Java objects successfully saved to Json file!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
