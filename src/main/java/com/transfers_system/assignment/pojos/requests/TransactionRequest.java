package com.transfers_system.assignment.pojos.requests;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @JsonProperty(value = "source_account_id")
    @NotBlank(message = "Source Account ID must not be blank")
    private String sourceAccountId;

    @JsonProperty(value = "destination_account_id")
    @NotBlank(message = "Destination Account ID must not be blank")
    private String destinationAccountId;

    @JsonProperty(value = "amount")
    @NotBlank(message = "Amount must not be blank")
    private BigDecimal amount;

}
