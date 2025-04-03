package com.example.DomguiaSimo_BankingApp.AuditLog;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String operation;
    private String message;
    private String status;
//    private String user;

    public AuditLog(){}
    public AuditLog(String operation, String message, String status, LocalDateTime now) {
        this.operation = operation;
        this.message = message;
        this.status = status;
//        this.user = user;
    }

    public void setId(Long id) {this.id = id;}
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
    public void setOperation(String operation) {this.operation = operation;}
    public void setMessage(String message) {this.message = message;}
    public void setStatus(String status) {this.status = status;}
//    public void setUser(String user) {this.user = user;}
    public Long getId() {return id;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public String getOperation() {return operation;}
    public String getMessage() {return message;}
    public String getStatus() {return status;}
//    public String getUser() {return user;}
}
