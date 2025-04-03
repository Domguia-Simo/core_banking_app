package com.example.DomguiaSimo_BankingApp.AuditLog;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class AuditLogAspect {

    @Autowired
    private AuditLogService auditLogService;

//    @Before("execution(* com.example.DomguiaSimo_BankingApp.Transaction.*.*(..))")
@Before("execution(* com.example.DomguiaSimo_BankingApp.*.AccountService.*(..)) || " +
        "execution(* com.example.DomguiaSimo_BankingApp.*.TransactionService.*(..)) ||" +
        "execution(* com.example.DomguiaSimo_BankingApp.*.UserService.*(..)) ||" +
        "execution(* com.example.DomguiaSimo_BankingApp.*.*.OperationService.*(..))"+
        "execution(* com.example.DomguiaSimo_BankingApp.*.*.FieldService.*(..))")

    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = userDetails != null ? userDetails.getUsername():"";

        String details = "Method: " + className + "." + methodName + ", Args: " + Arrays.toString(args);
    System.out.println(details);
        AuditLog al = new AuditLog();
        al.setMessage(methodName);
        al.setOperation(className);
        al.setStatus("called");
//        al.setUser(username);

        auditLogService.saveLog(al);
    }

}
