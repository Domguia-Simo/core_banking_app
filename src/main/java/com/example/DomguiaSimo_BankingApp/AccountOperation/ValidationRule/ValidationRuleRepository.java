package com.example.DomguiaSimo_BankingApp.AccountOperation.ValidationRule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRuleRepository extends JpaRepository<ValidationRule ,Long> {
}
