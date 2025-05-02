package com.transfers_system.assignment.entities.database.tables;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "source_account_id", nullable = false)
    private String sourceAccountId;

    @Column(name = "destination_account_id")
    private String destinationAccountId;

    @Column(nullable = false, precision = 20, scale = 5)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "SGD";

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String status = "PENDING";

    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}