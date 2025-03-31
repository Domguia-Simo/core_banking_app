package com.example.DomguiaSimo_BankingApp.Account;

import com.example.DomguiaSimo_BankingApp.Transaction.Transaction;

import java.util.List;
import java.util.Map;

public interface AccountServiceInterface {

//    CORE OPERATIONS
//    Crediting an account
    public Map<?,?> creditAccount(Long account_id , Transaction transaction);
//    Debiting an account
    public Map<?,?> debitAccount(Long account_id, Transaction transaction);
//    Account balance
    public Map<?,?> accountBalance(Long account_id);

//    =================

    public Map<?,?> createAccount(Long user_id ,Account account);
    public Account getAccount(Long id);
    public Account getUserAccount(Long user_id);
    public List<Account> getAccounts();
    public Boolean deleteAccount(Long id);
    public Boolean updateAccount(Long id ,Account account);
}
