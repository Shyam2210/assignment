package com.transfers_system.assignment.repository;
/*
 * Created by: Shyam Gupta
 * Date: 02/05/25
 * Project: assignment
 */

import com.transfers_system.assignment.entities.database.tables.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
