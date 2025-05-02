package com.transfers_system.assignment.pojos.response;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

public class TransactionResponse extends ErrorResponse{
    public TransactionResponse(boolean success, String message) {
        super(success, message);
    }
}
