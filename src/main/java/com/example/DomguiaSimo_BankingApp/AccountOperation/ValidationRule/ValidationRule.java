package com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Field.Field;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "validationRules")
public class ValidationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> parameters;

//    @ManyToOne
//    @JoinColumn(name = "field_id")
//    private Field field;

    public ValidationRule(){}
    public ValidationRule(String name ,Map<String, String> paramters){
        this.name = name;
        this.parameters = paramters;
    }

//    Setters

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setParameters(Map<String, String> parameters) {this.parameters = parameters;}

//Getters

    public Long getId() {return id;}

    public String getName() {return name;}

    public Map<String, String> getParameters() {return parameters;}
}
