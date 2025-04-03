package com.example.DomguiaSimo_BankingApp.AuditLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepo;

    public void saveLog(AuditLog log){
        log.setTimestamp(LocalDateTime.now());
        auditLogRepo.save(log);
    }

    public List<AuditLog> getLogs(){
        return auditLogRepo.findAll();
    }


}
