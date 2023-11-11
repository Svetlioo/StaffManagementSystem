package com.system;

import com.system.Controllers.Manager;
import com.system.Controllers.StaffManager;
import com.system.Models.Employee;
import com.system.Services.Service;
import com.system.Services.StaffService;

import java.util.Scanner;

import static com.system.Models.MenuDisplay.WelcomeMessage;
import static com.system.Models.MenuDisplay.displayCommands;

public class StaffManagementApp {
    public static void main(String[] args) {
        WelcomeMessage();
        try (Scanner scanner = new Scanner(System.in)) {
            Service<Employee> service = new StaffService(scanner);
            Manager manager = new StaffManager(service);
            boolean isRunning = true;
            while (isRunning) {
                displayCommands();
                String input = scanner.nextLine();
                // Just for the ide, it works without it, but gives me a yellow highlight.
                if (input.equals("8")) {
                    isRunning = false;
                }
                manager.execute(input);
            }
        }
        // The scanner is automatically closed (try-with-resources).
    }
}