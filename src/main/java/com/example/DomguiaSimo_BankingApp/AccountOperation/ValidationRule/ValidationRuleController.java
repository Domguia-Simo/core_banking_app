package com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/rule")
public class ValidationRuleController {

    @Autowired
    private ValidationRuleService validService;

    @PostMapping("/create-rule")
    ResponseEntity<?> createValidateRule(@Valid @RequestBody ValidationRule ValidateRule , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity(Map.of("error" ,errors) , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(validService.createValidationRule(ValidateRule) ,HttpStatus.OK);
    }

    @GetMapping("/get-rules")
    ResponseEntity<?> getValidateRules(){
        return new ResponseEntity<>(validService.getValidationRules() ,HttpStatus.OK);
    }

    @GetMapping("/get-rule/{id}")
    ResponseEntity<?> getValidateRule(@PathVariable("id") Long id){
        return new ResponseEntity<>(validService.getValidationRule(id) ,HttpStatus.OK);
    }

    @DeleteMapping("/delete-rule/{id}")
    ResponseEntity<?> deleteValidateRule(@PathVariable("id") Long id){
        Boolean result = validService.deleteValidationRule(id);
        if(result){
            return new ResponseEntity<>("rule deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid rule id") ,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-rule/{id}")
    ResponseEntity<?> updateValidateRule(@PathVariable("id") Long id, @Valid @RequestBody ValidationRule ValidateRule, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error" ,errors) ,HttpStatus.BAD_REQUEST);
        }
        Boolean result = validService.updateValidationRule(id, ValidateRule);
        if(result){
            return new ResponseEntity<>("rule deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid rule id") ,HttpStatus.BAD_REQUEST);
    }
    
}
