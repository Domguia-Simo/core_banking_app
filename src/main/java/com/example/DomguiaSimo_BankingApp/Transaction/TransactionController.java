package com.example.DomguiaSimo_BankingApp.Transaction;

import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Transaction creation endpoint")
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

    @Operation(summary = "Obtaining the list of transactions")
    @GetMapping("/get-transactions")
    ResponseEntity<?> getTransactions(){
        return ResponseEntity.ok(transService.getTransactions());
    }

    @Operation(summary = "Getting a particular transaction")
    @GetMapping("/get-transaction/{id}")
    ResponseEntity<?> getTransaction(@PathVariable("id") Long id){
        return ResponseEntity.ok(transService.getTransaction(id));
    }

    @Operation(summary = "Obtaining a transaction from its corresponding account id")
    @GetMapping("/get-account-transaction/{account_id}")
    ResponseEntity<?> getAccountTransaction(@PathVariable("account_id") Long account_id ,@RequestParam Map<String,String> options){
        TransactionType type = null;
        if(options.containsKey("type")){
            if(TransactionType.CREDIT.name().equals(options.get("type").toUpperCase())){
                type =TransactionType.CREDIT;
            }else if(TransactionType.DEBIT.name().equals(options.get("type").toUpperCase())){
                type = TransactionType.DEBIT;
            }
        }
        return ResponseEntity.ok(transService.getAccountTransaction(account_id ,type));
    }

    @Operation(summary = "Deleting a transaction")
    @DeleteMapping("/delete-transaction/{id}")
    ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id ){
        transService.deleteTransaction(id );
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @Operation(summary = "Updating a transaction")
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
