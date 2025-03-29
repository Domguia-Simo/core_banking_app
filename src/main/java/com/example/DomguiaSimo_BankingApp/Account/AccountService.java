package com.example.DomguiaSimo_BankingApp.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface{

    @Autowired
    AccountRepository accountRepo;

    @Override
    public void createAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public Account getAccount(Long id) {
        Optional<Account> Oa = accountRepo.findById(id);
        if(Oa.isPresent()){
            return Oa.get();
        }
        return null;
    }

    @Override
    public Account getUserAccount(Long user_id) {
        Optional<Account> Oa = accountRepo.findByUserId(user_id);
        if(Oa.isPresent()){
            return Oa.get();
        }
        return null;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> Oa = accountRepo.findById(id);
        if(Oa.isPresent()){
            accountRepo.delete(Oa.get());
        }
    }

    @Override
    public void updateAccount(Long id, Account account) {
        Optional<Account> Oa = accountRepo.findById(id);
        if(Oa.isPresent()){
            accountRepo.deleteById(id);
            accountRepo.save(account);
        }
    }
}
