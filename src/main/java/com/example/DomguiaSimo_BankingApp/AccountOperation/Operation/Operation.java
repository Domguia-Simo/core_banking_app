package com.example.DomguiaSimo_BankingApp.AccountOperation.Operation;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Field.Field;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Operation name require")
    String name;

    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    List<Field> fields;

    public Operation(){}

    public Operation(String name){
        this.name = name;
    }

//    Getter

    public Long getId() {return id;}

    public String getName(){return name;}

    public List<Field> getFields() {return fields;}

    //    Setter

    public void setId(Long id) {this.id = id;}

    public void setName(String name){this.name = name;}

    public void setFields(List<Field> fields) {this.fields = fields;}
}
