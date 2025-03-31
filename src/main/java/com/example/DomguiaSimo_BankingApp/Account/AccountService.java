package com.example.DomguiaSimo_BankingApp.Account;

import com.example.DomguiaSimo_BankingApp.Transaction.Transaction;
import com.example.DomguiaSimo_BankingApp.Transaction.TransactionRepository;
import com.example.DomguiaSimo_BankingApp.Transaction.TransactionType;
import com.example.DomguiaSimo_BankingApp.User.User;
import com.example.DomguiaSimo_BankingApp.User.UserRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface{

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private TransactionRepository transRepo;


//    CORE OPERATIONS

    @Override
    @Transactional
    public Map<?,?> creditAccount(Long account_id,Transaction transaction) {
        Optional<Account> Oa = accountRepo.findById(account_id);
        if(Oa.isPresent()){
            Account account = Oa.get();
            if(transaction.getAmount() > Account.max_depoit){
                return Map.of("error" ,"Maximum deposit: "+Account.max_depoit);
            }
            if(transaction.getDate() == null){
                transaction.setDate(new Date());
            }
            transaction.setAccount(account);
            transaction.setTransactionType(TransactionType.CREDIT);
            transRepo.save(transaction);
            account.setAmount(account.getAmount()+transaction.getAmount());
            Account result = accountRepo.save(account);

            return Map.of("success" , result);

        }
        return Map.of("error" ,"Invalid account id");
    }

    @Override
    @Transactional
    public Map<?,?> debitAccount(Long account_id, Transaction transaction) {
        Optional<Account> Oa = accountRepo.findById(account_id);
        if(Oa.isPresent()){
            Account account = Oa.get();
            if(transaction.getAmount() > account.getAmount()){
                return Map.of("error" ,"Insufficient balance");
            }
            if(transaction.getDate() == null){
                transaction.setDate(new Date());
            }
            transaction.setAccount(account);
            transaction.setTransactionType(TransactionType.DEBIT    );
            transRepo.save(transaction);
            account.setAmount(account.getAmount()-transaction.getAmount());
            Account result = accountRepo.save(account);

            return Map.of("success" , result);

        }
        return Map.of("error" ,"Invalid account id");

    }

    @Override
    public Map<?,?> accountBalance(Long account_id) {
        Optional<Account> Oa = accountRepo.findById(account_id);
        if(Oa.isPresent()){
            Float balance = Oa.get().getAmount();
            return Map.of("success" ,balance);
        }
        return Map.of("error","Invalid account id");
    }

//    =====================================


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
