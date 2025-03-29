package com.example.DomguiaSimo_BankingApp.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create-account")
    ResponseEntity<?> createAccount(@RequestBody Account account){
        accountService.createAccount(account);
        return new ResponseEntity("Account created correctly" , HttpStatus.CREATED);
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
        accountService.deleteAccount(id);
        return new ResponseEntity("Account deleted correctly" , HttpStatus.OK);
    }

    @PutMapping("/update-account/{id}")
    ResponseEntity<?> updateAccount(@PathVariable("id") Long id ,@RequestBody Account account){
        accountService.updateAccount(id ,account);
        return new ResponseEntity("Account updated correctly" , HttpStatus.OK);
    }
}
