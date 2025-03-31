package com.example.DomguiaSimo_BankingApp.AccountOperation.Operation;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Field.Field;
import com.example.DomguiaSimo_BankingApp.AccountOperation.Field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService implements OperationInterface{

    @Autowired
    private OperationRepository operationRepo;
    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public Operation createOperation(Operation operation) {
        Operation o = operationRepo.save(operation);
        return o;
    }

    @Override
    public List<Operation> getOperations() {
        return operationRepo.findAll();
    }

    @Override
    public Operation getOperation(Long id) {
        Optional<Operation> Oo = operationRepo.findById(id);
        if(Oo.isPresent()){
            return Oo.get();
        }
        return null;
    }

    @Override
    public Boolean deleteOperation(Long id) {
        Optional<Operation> Oo = operationRepo.findById(id);
        if(Oo.isPresent()){
            operationRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateOperation(Long id, Operation operation) {
        Optional<Operation> Oo = operationRepo.findById(id);
        if(Oo.isPresent()){
            operationRepo.deleteById(id);
            operationRepo.save(operation);
            return true;
        }
        return false;
    }
}
