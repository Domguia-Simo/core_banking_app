package com.example.DomguiaSimo_BankingApp.Account;

import java.util.List;
import java.util.Map;

public interface AccountServiceInterface {
    public Map<?,?> createAccount(Long user_id ,Account account);
    public Account getAccount(Long id);
    public Account getUserAccount(Long user_id);
    public List<Account> getAccounts();
    public Boolean deleteAccount(Long id);
    public Boolean updateAccount(Long id ,Account account);
}
