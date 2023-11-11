package com.system.Exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("No employee with that name!");
    }
}
