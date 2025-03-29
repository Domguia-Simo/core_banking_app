package com.example.DomguiaSimo_BankingApp.Transaction;

import java.util.List;

public interface TransactionServiceInterface {
    public void createTransaction(Transaction trans);
    public List<Transaction> getTransactions();
    public Transaction getTransaction(Long id);
    public List<Transaction> getAccountTransaction(Long account_id);
    public void deleteTransaction(Long id);
    public void updateTransaction(Long id,Transaction trans);
}
