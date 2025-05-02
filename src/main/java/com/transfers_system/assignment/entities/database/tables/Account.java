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
@Table(name = "accounts")
@Data
public class Account {
    @Id
    private String accountId;

    private BigDecimal balance;

    private String currency = "SGD";

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}