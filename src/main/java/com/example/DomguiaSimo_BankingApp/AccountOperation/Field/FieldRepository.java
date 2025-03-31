package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field ,Long> {
}
