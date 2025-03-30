package com.example.DomguiaSimo_BankingApp.Transaction;

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
@RequestMapping(value = "/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transService;

    @PostMapping("/create-transaction/{account_id}")
    ResponseEntity<?> createTransaction(@PathVariable("account_id") Long account_id, @Valid @RequestBody Transaction trans , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("errors",errors) , HttpStatus.BAD_REQUEST);
        }
        Map<?,?> result = transService.createTransaction( account_id,trans);
        if(result.containsKey("success")){
            return ResponseEntity.ok(result.get("success"));
        }
        return new ResponseEntity(result.get("failed") ,HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/get-transactions")
    ResponseEntity<?> getTransactions(){
        return ResponseEntity.ok(transService.getTransactions());
    }

    @GetMapping("/get-transaction/{id}")
    ResponseEntity<?> getTransaction(@PathVariable("id") Long id){
        return ResponseEntity.ok(transService.getTransaction(id));
    }

    @GetMapping("/get-account-transaction/{account_id}")
    ResponseEntity<?> getAccountTransaction(@PathVariable("account_id") Long account_id){
        return ResponseEntity.ok(transService.getAccountTransaction(account_id));
    }

    @DeleteMapping("/delete-transaction/{id}")
    ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id ){
        transService.deleteTransaction(id );
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @PutMapping("/update-transaction/{id}")
    ResponseEntity<?> updateTransaction(@PathVariable("id") Long id ,@Valid @RequestBody Transaction trans ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(errors ,HttpStatus.BAD_REQUEST);
        }
        Boolean b = transService.updateTransaction(id, trans);
        return b ? ResponseEntity.ok("Transaction updated successfully"): new ResponseEntity("Invalid transaction id" ,HttpStatus.BAD_REQUEST);
    }

}
