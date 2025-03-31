package com.example.DomguiaSimo_BankingApp.AccountOperation.Operation;

import java.util.List;

public interface OperationInterface {
    public Operation createOperation(Operation operation);
    public List<Operation> getOperations();
    public Operation getOperation(Long id);
    public Boolean deleteOperation(Long id);
    public Boolean updateOperation(Long id ,Operation operation);
}
