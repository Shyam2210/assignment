package com.transfers_system.assignment.exceptions.custom;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

public class AccountDoeNotExistException extends RuntimeException {
    public AccountDoeNotExistException(String message) {
        super(message);
    }
}