package com.system.Exceptions;

public class InvalidStringException extends Exception {
    public InvalidStringException() {
        super("Invalid name! Please enter a non-empty string containing characters only.");
    }

}
