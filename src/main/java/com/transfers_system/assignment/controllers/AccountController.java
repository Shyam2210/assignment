package com.transfers_system.assignment.controllers;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */

import com.transfers_system.assignment.entities.database.tables.Account;
import com.transfers_system.assignment.pojos.requests.AddAccountRequest;
import com.transfers_system.assignment.pojos.requests.TransactionRequest;
import com.transfers_system.assignment.pojos.response.AddAccountResponse;
import com.transfers_system.assignment.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/assignment/accounts")
    private ResponseEntity<?> addAccount(@Valid @RequestBody AddAccountRequest request){
        Account account = accountService.addNewAccount(request);
        return ResponseEntity.ok(new AddAccountResponse(true, "Account created Successfully."));
    }

    @GetMapping(value = "/assignment/accounts/{accountId}")
    private ResponseEntity<?> getAccountDetails(@PathVariable(required = true) String accountId){
        return ResponseEntity.ok(accountService.getAccountDetails(accountId));
    }

    @PostMapping(value = "/assignment/transactions")
    private ResponseEntity<?> transaction(@Valid @RequestBody TransactionRequest request){
        return ResponseEntity.ok(accountService.transact(request));
    }
}
