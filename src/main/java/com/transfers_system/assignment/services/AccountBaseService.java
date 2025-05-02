package com.transfers_system.assignment.services;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */


import com.transfers_system.assignment.entities.database.tables.Account;
import com.transfers_system.assignment.exceptions.custom.AccountAlreadyExistsException;
import com.transfers_system.assignment.pojos.requests.AddAccountRequest;
import com.transfers_system.assignment.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountBaseService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

}
