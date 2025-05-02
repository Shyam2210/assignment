package com.transfers_system.assignment.pojos.response;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private boolean success;
    private String message;
}
