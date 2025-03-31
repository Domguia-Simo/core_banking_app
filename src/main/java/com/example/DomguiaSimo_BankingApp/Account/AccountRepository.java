package com.example.DomguiaSimo_BankingApp.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account ,Long> {

    @Query(value = "select * from accounts where user_id =?1" ,nativeQuery = true)
    public Optional<Account> findByUserId(Long id);

}
