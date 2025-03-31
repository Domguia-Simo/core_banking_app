package com.example.DomguiaSimo_BankingApp.AccountOperation.Operation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @PostMapping("/create-operation")
    ResponseEntity<?> createOperation(@Valid @RequestBody Operation operation , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity(Map.of("error" ,errors) , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(operationService.createOperation(operation) ,HttpStatus.OK);
    }

    @GetMapping("/get-operations")
    ResponseEntity<?> getOperations(){
        return new ResponseEntity<>(operationService.getOperations() ,HttpStatus.OK);
    }

    @GetMapping("/get-operation/{id}")
    ResponseEntity<?> getOperation(@PathVariable("id") Long id){
        return new ResponseEntity<>(operationService.getOperation(id) ,HttpStatus.OK);
    }

    @DeleteMapping("/delete-operation/{id}")
    ResponseEntity<?> deleteOperation(@PathVariable("id") Long id){
        Boolean result = operationService.deleteOperation(id);
        if(result){
            return new ResponseEntity<>("Operation deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid operation id") ,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-operation/{id}")
    ResponseEntity<?> updateOperation(@PathVariable("id") Long id, @Valid @RequestBody Operation operation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error" ,errors) ,HttpStatus.BAD_REQUEST);
        }
        Boolean result = operationService.updateOperation(id, operation);
        if(result){
            return new ResponseEntity<>("Operation deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid operation id") ,HttpStatus.BAD_REQUEST);
    }
}
