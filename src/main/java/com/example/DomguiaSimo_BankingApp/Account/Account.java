package com.example.DomguiaSimo_BankingApp.Account;

import jakarta.persistence.*;

import java.util.Date;

enum Status{
    ACTIVE, BLOCKED, SUSPENDED
}
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Status status;
    private Float amount;
    private Date created_at = new Date();

//    @OneToOne
    Long userId;

    public Account(){}
    public Account(Status status ,Float amount ){
        this.amount = amount;
        this.status = status;
    }

//    Setters


    public void setId(Long id) {this.id = id;}
    public void setUserId(Long userId) {this.userId = userId;}
    public void setCreated_at(Date date){this.created_at = date;}
    public void setAmount(Float amount){this.amount = amount;}
    public void setStatus(Status status){this.status = status;}

//    Getters

    public Long getId() {return id;}
    public Long getUserId(){return userId;}
    public Date getCreated_at(){return this.created_at;}
    public Status getStatus(){return this.status;}
    public Float getAmount(){return this.amount;}
}
