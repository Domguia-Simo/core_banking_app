package com.example.DomguiaSimo_BankingApp.Transaction;

import com.example.DomguiaSimo_BankingApp.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction ,Long> {

//    @Query(value = "select * from transactions where account_id= ?1" ,nativeQuery = true)
    public List<Transaction> findByAccount(Account account);

}
