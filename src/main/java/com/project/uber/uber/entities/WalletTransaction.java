package com.project.uber.uber.entities;

import com.project.uber.uber.entities.enums.TransactionMethod;
import com.project.uber.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private String transactionId;
    @CreationTimestamp
    private LocalDateTime transactionDateAndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;
}
