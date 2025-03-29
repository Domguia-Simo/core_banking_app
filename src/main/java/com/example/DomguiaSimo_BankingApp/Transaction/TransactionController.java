package com.example.DomguiaSimo_BankingApp.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transService;

    @PostMapping("/create-transaction")
    ResponseEntity<?> createTransaction(@RequestBody Transaction trans){
        transService.createTransaction(trans);
        return ResponseEntity.ok("Transaction save successfully");
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
    ResponseEntity<?> updateTransaction(@PathVariable("id") Long id ,@RequestBody Transaction trans){
        transService.updateTransaction(id, trans);
        return ResponseEntity.ok("Transaction updated successfully");
    }

}
