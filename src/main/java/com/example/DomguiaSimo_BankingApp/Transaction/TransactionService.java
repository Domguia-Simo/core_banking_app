package com.example.DomguiaSimo_BankingApp.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements  TransactionServiceInterface{

    @Autowired
    private TransactionRepository transRepo;

    @Override
    public void createTransaction(Transaction trans) {
        transRepo.save(trans);
    }

    @Override
    public List<Transaction> getTransactions() {

        return transRepo.findAll();
    }

    @Override
    public Transaction getTransaction(Long id) {
        Optional<Transaction> Ot = transRepo.findById(id);
        if(Ot.isPresent()){
            return Ot.get();
        }
        return null;
    }

    @Override
    public List<Transaction> getAccountTransaction(Long account_id) {
        Optional<List<Transaction>> Ot = transRepo.findByAccountId(account_id);
        if(Ot.isPresent()){
            return Ot.get();
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transRepo.deleteById(id);
    }

    @Override
    public void updateTransaction(Long id, Transaction trans) {
        Optional<Transaction> Ot = transRepo.findById(id);
        if(Ot.isPresent()){
            transRepo.deleteById(id);
            transRepo.save(trans);
        }
    }
}
