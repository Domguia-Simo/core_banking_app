package com.example.DomguiaSimo_BankingApp.Transaction;

import com.example.DomguiaSimo_BankingApp.Account.Account;
import com.example.DomguiaSimo_BankingApp.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionService implements  TransactionServiceInterface{

    @Autowired
    private TransactionRepository transRepo;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Map<?,?> createTransaction(Long account_id , Transaction trans) {
        Optional<Account> Oa = accountRepository.findById(account_id);
        if(Oa.isPresent()){
            trans.setAccount(Oa.get());
            Transaction t = transRepo.save(trans);
            return Map.of("success",t);
        }
        return Map.of("failed" ,"Invalid account id");
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
        Optional<Account> Oa = accountRepository.findById(account_id);
        if(Oa.isPresent()){
            Account account = Oa.get();
            List<Transaction> transactions = transRepo.findByAccount(account);
            return transactions;
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transRepo.deleteById(id);
    }

    @Override
    public Boolean updateTransaction(Long id, Transaction trans) {
        Optional<Transaction> Ot = transRepo.findById(id);
        if(Ot.isPresent()){
            trans.setAccount(Ot.get().getAccount());
            transRepo.deleteById(id);
            transRepo.save(trans);
        return true;
        }else{
            return false;
        }
    }
}
