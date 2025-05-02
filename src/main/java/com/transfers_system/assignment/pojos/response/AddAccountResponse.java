package com.transfers_system.assignment.pojos.response;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

import lombok.Data;

public class AddAccountResponse extends ErrorResponse{
    public AddAccountResponse(boolean success, String message) {
        super(success, message);
    }
}
