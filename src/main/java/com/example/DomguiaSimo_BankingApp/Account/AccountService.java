package com.example.DomguiaSimo_BankingApp.Account;

import com.example.DomguiaSimo_BankingApp.User.User;
import com.example.DomguiaSimo_BankingApp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface{

    @Autowired
    AccountRepository accountRepo;
    @Autowired
    UserRepository  userRepository;

    @Override
    public Map<?,?> createAccount(Long user_id ,Account account) {
        Optional<User> Ou = userRepository.findById(user_id);
        if(Ou.isPresent()){
            account.setUser(Ou.get());
            Account result = accountRepo.save(account);
            return Map.of("success" ,result);
        }else{
            return Map.of("failed" ,"Invalid user_id");
        }
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
    public Boolean deleteAccount(Long id) {
        Optional<Account> Oa = accountRepo.findById(id);
        if(Oa.isPresent()){
            accountRepo.delete(Oa.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateAccount(Long id, Account account) {
        Optional<Account> Oa = accountRepo.findById(id);
        if(Oa.isPresent()){
            account.setUser(Oa.get().user);
            accountRepo.deleteById(id);
            accountRepo.save(account);
        return true;
        }
        return false;
    }
}
