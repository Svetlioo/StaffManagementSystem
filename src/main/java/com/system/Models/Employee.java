package com.system.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Employee {
    private long id;
    private String name;
    private Date startDate;
    private String department;
    private String role;
    private double salary;

    public Employee(long id, String name, Date startDate, String department, String role, double salary) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        // Making the date in bg format because we are from Bulgaria :)
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("bg-BG"));
        String formattedDate = sdf.format(startDate);
        return "+" + "-".repeat(42) + "+\n" +
                String.format("| %-40s |%n", "Employee ID: " + id) +
                String.format("| %-40s |%n", "Name: " + name) +
                String.format("| %-40s |%n", "Start Date: " + formattedDate) +
                String.format("| %-40s |%n", "Department: " + department) +
                String.format("| %-40s |%n", "Role: " + role) +
                String.format("| %-40s |%n", "Salary: " + salary) +
                "+" + "-".repeat(42) + "+";
    }

    //    This equals method is to ensure when adding to the hashmap that there are no employee with the same id.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
