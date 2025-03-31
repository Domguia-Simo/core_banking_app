package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.Operation;
import com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule.ValidationRule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

//Different data type
enum Type{
    TEXT, INT ,DECIMAL
}


@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private Type type;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> condition;
    @ManyToOne
    @JoinColumn(name="operation_id")
    @JsonIgnore
    private Operation operation;

//    @OneToMany
//    private List<ValidationRule> validationRule;

    public Field(){}
    public Field(String name ,Type type ,Map<String, String> condition){
        this.name = name;
        this.type = type;
        this.condition = condition;
    }

//    Setters
    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setType(Type type){this.type = type;}

    public void setOperation(Operation operation) {this.operation = operation;}

    public void setCondition(Map<String, String> condition) {this.condition = condition;}

    //    public void setValidationRule(List<ValidationRule> validationRule) {
//        this.validationRule = validationRule;
//    }

    //    Getter
    public Long getId(){return id;}
    public String getName(){return name;}
    public Type getType(){return  type;}

    public Map<String, String> getCondition() {return condition;}

    public Operation getOperation() {return operation;}
    //    public List<ValidationRule> getValidationRule() {
//        return validationRule;
//    }
}
