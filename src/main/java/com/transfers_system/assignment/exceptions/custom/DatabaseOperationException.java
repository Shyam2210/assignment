package com.transfers_system.assignment.exceptions.custom;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */


public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
