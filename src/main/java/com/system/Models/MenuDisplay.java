package com.system.Models;

import java.util.Scanner;

public class MenuDisplay {
    public static void displayCommands() {
        System.out.println("+" + "-".repeat(35) + "+");
        System.out.printf("| %-2s %-30s |%n", "1.", "Add Employee");
        System.out.printf("| %-2s %-30s |%n", "2.", "Edit Employee");
        System.out.printf("| %-2s %-30s |%n", "3.", "List All Employees");
        System.out.printf("| %-2s %-30s |%n", "4.", "Search Employee by ID");
        System.out.printf("| %-2s %-30s |%n", "5.", "Search Employee by Name");
        System.out.printf("| %-2s %-30s |%n", "6.", "Search Employee by Department");
        System.out.printf("| %-2s %-30s |%n", "7.", "Fire Employee");
        System.out.printf("| %-2s %-30s |%n", "8.", "Exit & Save");
        System.out.println("+" + "-".repeat(35) + "+");
        System.out.print("Enter your choice: ");
    }

    public static void WelcomeMessage() {
        System.out.println("Welcome to your Staff Management System!");
    }

    public static void exitMessage() {
        System.out.println("Exiting the system...");
    }

    public static void Press0ToExit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 0 to go back to main menu!");
        String input = scanner.nextLine();
        while (!input.equals("0")) {
            System.out.println("Press 0 to go back to main menu!");
            input = scanner.nextLine();
        }
        // I do not close the scanner because then I have to create a new scanner everytime.
        // I close it at the end and everything works.

    }
}
