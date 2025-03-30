package com.example.DomguiaSimo_BankingApp.Account;

import com.example.DomguiaSimo_BankingApp.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

enum Status{
    ACTIVE, BLOCKED, SUSPENDED
}
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    @PositiveOrZero(message = "No negative number")
    private Float amount;
    private Date created_at = new Date();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;

    public Account(){}
    public Account(Status status ,Float amount ){
        this.amount = amount;
        this.status = status;
    }

//    Setters


    public void setId(Long id) {this.id = id;}
    public void setUser(User userId) {this.user = userId;}
    public void setCreated_at(Date date){this.created_at = date;}
    public void setAmount(Float amount){this.amount = amount;}
    public void setStatus(Status status){this.status = status;}

//    Getters

    public Long getId() {return id;}
    public User getUserId(){return user;}
    public Date getCreated_at(){return this.created_at;}
    public Status getStatus(){return this.status;}
    public Float getAmount(){return this.amount;}
}
