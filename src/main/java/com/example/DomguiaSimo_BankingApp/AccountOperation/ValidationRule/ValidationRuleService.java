package com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationRuleService implements ValidationRuleInterface{

    @Autowired
    private ValidationRuleRepository validRepo;

    @Override
    public ValidationRule createValidationRule(ValidationRule validationRule) {
        ValidationRule vr = validRepo.save(validationRule);
        return vr;
    }

    @Override
    public List<ValidationRule> getValidationRules() {
        return validRepo.findAll();
    }

    @Override
    public ValidationRule getValidationRule(Long id) {
        Optional<ValidationRule> Ov = validRepo.findById(id);
        if(Ov.isPresent()){
            return Ov.get();
        }
        return null;
    }

    @Override
    public Boolean deleteValidationRule(Long id) {
        Optional<ValidationRule> Ov = validRepo.findById(id);
        if(Ov.isPresent()){
            validRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateValidationRule(Long id, ValidationRule validationRule) {
        Optional<ValidationRule> Ov = validRepo.findById(id);
        if(Ov.isPresent()){
            validRepo.deleteById(id);
            validRepo.save(validationRule);
            return true;
        }
        return false;
    }
}
