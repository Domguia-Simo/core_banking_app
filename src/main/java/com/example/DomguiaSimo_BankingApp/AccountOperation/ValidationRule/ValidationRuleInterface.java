package com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.Operation;

import java.util.List;

public interface ValidationRuleInterface {
    public ValidationRule createValidationRule(ValidationRule validationRule);
    public List<ValidationRule> getValidationRules();
    public ValidationRule getValidationRule(Long id);
    public Boolean deleteValidationRule(Long id);
    public Boolean updateValidationRule(Long id ,ValidationRule validationRule);
}
