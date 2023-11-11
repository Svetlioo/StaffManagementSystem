package com.system.Exceptions;

public class DuplicateIDException extends Exception {
    public DuplicateIDException() {
        super("ID already exists!");
    }
}
