package com.system.Exceptions;

public class BelowMinimumSalaryException extends Exception {
    public BelowMinimumSalaryException() {
        super("The minimum salary in Bulgaria is 780 BGN!");
    }
}
