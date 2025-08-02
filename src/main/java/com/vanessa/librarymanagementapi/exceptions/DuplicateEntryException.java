package com.vanessa.librarymanagementapi.exceptions;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
