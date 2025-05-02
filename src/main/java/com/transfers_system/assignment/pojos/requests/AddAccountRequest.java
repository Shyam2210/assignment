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
import java.util.UUID;

@Data
public class AddAccountRequest {

    @JsonProperty(value = "account_id")
    @NotBlank(message = "Account ID must not be blank")
    private String accountId;

    private String currency;

    @JsonProperty(value = "initial_balance")
    @NotBlank(message = "Initial Balance must not be blank")
    private BigDecimal initialBalance;

}
