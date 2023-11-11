package com.system.Exceptions;

public class InvalidDepartmentException extends Exception {
    public InvalidDepartmentException() {
        super("No such department!");
    }
}
