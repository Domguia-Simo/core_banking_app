package com.example.DomguiaSimo_BankingApp.Account;

import java.util.List;
import java.util.Map;

public interface AccountServiceInterface {
    public void createAccount(Account account);
    public Account getAccount(Long id);
    public Account getUserAccount(Long user_id);
    public List<Account> getAccounts();
    public void deleteAccount(Long id);
    public void updateAccount(Long id ,Account account);
}
