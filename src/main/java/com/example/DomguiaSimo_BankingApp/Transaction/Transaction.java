package com.example.DomguiaSimo_BankingApp.Transaction;

import jakarta.persistence.*;

import java.util.Date;

enum TransactionType{
    DEBIT ,CREDIT
}
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToMany
    private Long accountId;

    private TransactionType transactionType;

    private Date date;

    private Float amount;

    public Transaction(){}
    public Transaction(Long account_id ,Date date ,TransactionType tt, Float amount){
        this.accountId = account_id;
        this.amount = amount;
        this.transactionType = tt;
        this.date = date;
    }

//    Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountId(Long account_id) {
        this.accountId = account_id;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

//    Getters


    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getDate() {
        return date;
    }

    public Float getAmount() {
        return amount;
    }
}
