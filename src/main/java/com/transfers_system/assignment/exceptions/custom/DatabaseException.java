package com.transfers_system.assignment.exceptions.custom;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}