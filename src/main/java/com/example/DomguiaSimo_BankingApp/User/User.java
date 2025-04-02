package com.example.DomguiaSimo_BankingApp.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {
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

    private Role role;

    private List<GrantedAuthority> authorities = null;

    public User(){}
    public User(String name ,String email ,String password ){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority(Role.USER.name()));
    }

//    Setter
    public void setId(Long id){this.id = id;}
    public void setEmail(String email){this.email = email;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setToken(String token){this.token = token;}

    public void setRole(Role role) {this.role = role;}

    //    Getter
    public Long getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword(){return password;}

    @Override
    public String getUsername() {
        return email;
    }

    public String getToken(){return token;}

    public Role getRole() {return role;}
}
