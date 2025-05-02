package com.transfers_system.assignment.pojos.response;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GetAccountDetailsResponse{

    @JsonProperty(value = "account_id")
    @NotBlank(message = "Account ID must not be blank")
    private String accountId;

    @JsonProperty(value = "initial_balance")
    @NotBlank(message = "Initial Balance must not be blank")
    private BigDecimal initialBalance;

}
