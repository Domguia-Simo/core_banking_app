package com.example.DomguiaSimo_BankingApp.Account;

import com.example.DomguiaSimo_BankingApp.Transaction.Transaction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


//    CORE TASK/OPERATIONS
//    Crediting an account (adding money)
    @PostMapping("/credit-account/{id}")
    ResponseEntity<?> creditAccount(@PathVariable("id") Long id, @Valid @RequestBody Transaction trans, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error" ,errors) ,HttpStatus.BAD_REQUEST);
        }
        Map<?,?> result = accountService.creditAccount(id ,trans);
        if(result.containsKey("success")){
            return new ResponseEntity<>(result.get("success") ,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Map.of("error",result.get("error")) ,HttpStatus.BAD_REQUEST);
        }
    }

//    Debiting an account (removing money)
    @PostMapping("/debit-account/{id}")
    ResponseEntity<?> debitAccount(@PathVariable("id") Long id, @Valid @RequestBody Transaction trans, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error" ,errors) ,HttpStatus.BAD_REQUEST);
        }
        Map<?,?> result = accountService.debitAccount(id ,trans);
        if(result.containsKey("success")){
            return new ResponseEntity<>(result.get("success") ,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Map.of("error",result.get("error")) ,HttpStatus.BAD_REQUEST);
        }
    }

//    account balance
    @GetMapping("/balance/{id}")
    ResponseEntity<?> getBalance(@PathVariable("id") Long id){
        Map<?,?> result = accountService.accountBalance(id);
        if(result.containsKey("success")){
            return new ResponseEntity<>( Map.of("balance",result.get("success")),HttpStatus.OK);
        }else{
            return new ResponseEntity<>( result.get("error"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-account/{user_id}")
    ResponseEntity<?> createAccount(@PathVariable("user_id") Long user_id ,@Valid @RequestBody Account account , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField() ,error.getDefaultMessage()));
            return new ResponseEntity(Map.of("error",errors) ,HttpStatus.BAD_REQUEST);
        }
        Map<?,?> result = accountService.createAccount(user_id ,account);
        if (result.containsKey("success")) {
            return new ResponseEntity(result.get("success") , HttpStatus.CREATED);
        } else {
            return new ResponseEntity(result.get("failed") , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account/{id}")
    ResponseEntity<?> getAccount(@PathVariable("id") Long id){
        return new ResponseEntity(accountService.getAccount(id) , HttpStatus.OK);
    }

    @GetMapping("/get-accounts")
    ResponseEntity<?> getAccounts(){
        return new ResponseEntity(accountService.getAccounts() , HttpStatus.OK);
    }

    @GetMapping("/get-user-account/{user_id}")
    ResponseEntity<?> getUserAccount(@PathVariable("user_id") Long id){
        return new ResponseEntity(accountService.getUserAccount(id) , HttpStatus.OK);
    }

    @DeleteMapping("/delete-account/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        Boolean result = accountService.deleteAccount(id);
        if(result){
            return new ResponseEntity("Account deleted correctly" , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update-account/{id}")
    ResponseEntity<?> updateAccount(@PathVariable("id") Long id ,@Valid @RequestBody Account account ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField() ,error.getDefaultMessage()));
            return new ResponseEntity("" ,HttpStatus.BAD_REQUEST);
        }
        Boolean result  = accountService.updateAccount(id ,account);
        if(result){
            return new ResponseEntity("Account updated correctly" , HttpStatus.OK);
        }
        return new ResponseEntity( HttpStatus.BAD_REQUEST);

    }
}
