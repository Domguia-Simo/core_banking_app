package com.example.DomguiaSimo_BankingApp.Transaction;

import com.example.DomguiaSimo_BankingApp.Account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    private TransactionType transactionType;

//    @NotBlank(message = "Date required")
    private Date date;

    @Positive(message = "No zero or negative number")
    @NotNull(message = "amount required")
    private Float amount;

    public Transaction(){}
    public Transaction(Account account , Date date , TransactionType tt, Float amount){
        this.account = account;
        this.amount = amount;
        this.transactionType = tt;
        this.date = date;
    }

//    Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(Account account_id) {
        this.account = account_id;
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

    public Account getAccount() {
        return account;
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
