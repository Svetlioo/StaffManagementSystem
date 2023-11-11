package com.system.Exceptions;

public class InvalidIDException extends Exception {
    public InvalidIDException() {
        // This inherits the message, and it has the getMessage method from the Exception superclass.
        // Learned it last week in TU Sofia :)
        super("ID not found!");
    }
}
