package com.example.DomguiaSimo_BankingApp.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionServiceInterface {
    public Map<?,?> createTransaction(Long account_id ,Transaction trans);
    public List<Transaction> getTransactions();
    public Transaction getTransaction(Long id);
    public List<Transaction> getAccountTransaction(Long account_id ,TransactionType type);
    public void deleteTransaction(Long id);
    public Boolean updateTransaction(Long id,Transaction trans);
}
