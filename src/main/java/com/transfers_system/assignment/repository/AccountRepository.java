package com.transfers_system.assignment.repository;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */

import com.transfers_system.assignment.entities.database.tables.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // You can add custom queries if needed
}