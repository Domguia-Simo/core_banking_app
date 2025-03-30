package com.example.DomguiaSimo_BankingApp.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.grammars.hql.HqlParser;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name required")
    @NotBlank(message = "Name required")
    private String name;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email required")
    private String email;
    @NotNull(message = "Password required")
    @NotBlank(message = "Password required")
    private String password;
    private String token;

    public User(){}
    public User(String name ,String email ,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

//    Setter
    public void setId(Long id){this.id = id;}
    public void setEmail(String email){this.email = email;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setToken(String token){this.token = token;}

//    Getter
    public Long getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getToken(){return token;}


}
