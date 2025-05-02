package com.transfers_system.assignment.services;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */


import com.transfers_system.assignment.constants.TransactionStatus;
import com.transfers_system.assignment.constants.TransactionType;
import com.transfers_system.assignment.entities.database.tables.Account;
import com.transfers_system.assignment.entities.database.tables.Transaction;
import com.transfers_system.assignment.exceptions.custom.*;
import com.transfers_system.assignment.pojos.requests.AddAccountRequest;
import com.transfers_system.assignment.pojos.requests.TransactionRequest;
import com.transfers_system.assignment.pojos.response.GetAccountDetailsResponse;
import com.transfers_system.assignment.pojos.response.TransactionResponse;
import com.transfers_system.assignment.repository.AccountRepository;
import com.transfers_system.assignment.repository.TransactionRepository;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AccountService extends AccountBaseService {

    @Value("${default.currency}")
    private String defaultCurrency;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void addNewAccount(AddAccountRequest request) {
        returnIfNullOrEmpty(request.getAccountId(), "Account Id must be valid.");
        Account existingAccount = getAccountById(request.getAccountId());
        if (existingAccount != null) {
            throw new AccountAlreadyExistsException("Account already exists for Account ID: " + request.getAccountId());
        }

        Account newAccount = new Account();
        newAccount.setAccountId(request.getAccountId());
        newAccount.setBalance(request.getInitialBalance() != null ? request.getInitialBalance() : BigDecimal.ZERO);
        newAccount.setCurrency(request.getCurrency() != null ? request.getCurrency() : defaultCurrency);

        try {
            accountRepository.save(newAccount);
        } catch (DataAccessException | PersistenceException ex) {
            throw new DatabaseOperationException("Failed to save new account to the database", ex);
        }
    }

    public GetAccountDetailsResponse getAccountDetails(String accountId) {
        returnIfNullOrEmpty(accountId, "Account Id must be valid.");
        Account existingAccount = getAccountById(accountId);
        returnIfAccountDoesNotExist(existingAccount, accountId);
        return new GetAccountDetailsResponse(accountId, existingAccount.getBalance());
    }

    public TransactionResponse transact(TransactionRequest request) {
        returnIfNullOrEmpty(request.getSourceAccountId(), "Source Account Id must be valid.");
        returnIfNullOrEmpty(request.getDestinationAccountId(), "Destination Account Id must be valid.");

        //Log a transaction request
        Transaction transaction = transactionRepository.save(getTransactionEntity(request, TransactionType.TRANSFER));

        // Check if Source account is a valid Account
        Account sourceAccount = getAccountById(request.getSourceAccountId());
        returnIfAccountDoesNotExist(sourceAccount, request.getSourceAccountId(), transaction);

        // Check if Destination account is a valid Account
        Account destinationAccount = getAccountById(request.getDestinationAccountId());
        returnIfAccountDoesNotExist(destinationAccount, request.getDestinationAccountId(), transaction);

        //Check if source account has sufficient balance to transfer
        if (!isSufficientBalance(sourceAccount.getBalance(), request.getAmount())) {
            log.error("Insufficient balance in source account for account ID " + request.getSourceAccountId());
            updateTransactionDetailsInDatabase(transaction, TransactionStatus.FAILED, "Insufficient balance in source account.");
            throw new InSufficientBalanceException("Insufficient balance in source account.");
        }

        //Update the balance in source Account
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(request.getAmount()));

        //Update the balance in Destination Account
        destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));

        //save both the accounts together and transaction log
        try {
            log.info("Updating source and destination account details.");
            List<Account> accounts = Arrays.asList(sourceAccount, destinationAccount);
            accountRepository.saveAll(accounts);
            log.info("Updating transaction status.");
            updateTransactionDetailsInDatabase(transaction, TransactionStatus.SUCCESS, "Transaction successful!");
            return new TransactionResponse(true, "Transaction successful!");
        } catch (DataAccessException | PersistenceException ex) {
            log.error("Exception received while saving records. " + ex.getLocalizedMessage());
            throw new DatabaseOperationException("Failed to save account to the database", ex);
        }
    }

    private void returnIfAccountDoesNotExist(Account account, String accountId) {
        if (account == null) {
            log.error("No Account found for account ID: " + accountId);
            throw new AccountDoeNotExistException("Account does not exist for Account ID: " + accountId);
        }
    }

    private void returnIfAccountDoesNotExist(Account account, String accountId, Transaction transaction) {
        if (account == null) {
            log.error("No Account found for account ID: " + accountId);
            updateTransactionDetailsInDatabase(transaction, TransactionStatus.FAILED, "Account does not exist for Account ID: " + accountId);
            throw new AccountDoeNotExistException("Account does not exist for Account ID: " + accountId);
        }
    }

    private void updateTransactionDetailsInDatabase(Transaction transaction, TransactionStatus transactionStatus, String description) {
        log.info(String.format("Updating transaction status for %s to %s with description %s.", transaction.getId(), transactionStatus.toString(), description));
        transaction.setStatus(transactionStatus.toString());
        transaction.setDescription(description);
        transactionRepository.save(transaction);
    }

    private static void returnIfNullOrEmpty(String accountId, String message) {
        if (null == accountId || accountId.isEmpty()) {
            log.error("Account Id is Null or Empty.");
            throw new InvalidRequestException(message);
        }
    }

    private Transaction getTransactionEntity(TransactionRequest request, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType.toString());
        transaction.setAmount(request.getAmount());
        transaction.setSourceAccountId(request.getSourceAccountId());
        transaction.setDestinationAccountId(request.getDestinationAccountId());
        return transaction;
    }

    private boolean isSufficientBalance(BigDecimal balance, BigDecimal amountToBeDebited) {
        int result = balance.compareTo(amountToBeDebited);
        return result >= 0;
    }
}
