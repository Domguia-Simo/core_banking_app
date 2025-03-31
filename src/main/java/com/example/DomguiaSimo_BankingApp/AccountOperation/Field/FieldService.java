package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.Operation;
import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FieldService implements FieldInterface{

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private OperationRepository operationRepo;

    @Override
    @Transactional
    public List<Field> createField(List<Field> field ,Long operation_id) {
        Optional<Operation> Oo = operationRepo.findById(operation_id);
        List<Field> result = new ArrayList<>();
        if(Oo.isPresent()){
            Operation op = Oo.get();
//            operationRepo.deleteById(op.getId());

            for(Field f:field){
                f.setOperation(op);
                Field new_f = fieldRepository.save(f);
                result.add(new_f);
            }
            op.setFields(result);
            operationRepo.save(op);
        }
        return result;
    }

    @Override
    public List<Field> getFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field getField(Long id) {
        Optional<Field> Of = fieldRepository.findById(id);
        if(Of.isPresent()){
            return Of.get();
        }
        return null;
    }

    @Override
    public Boolean deleteField(Long id) {
        Optional<Field> Of = fieldRepository.findById(id);
        if(Of.isPresent()){
            fieldRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateField(Long id, Field field) {
        Optional<Field> Of = fieldRepository.findById(id);
        if(Of.isPresent()){
            Field f = Of.get();
            f.setCondition(field.getCondition());
            f.setName(field.getName());
            f.setType(field.getType());
            fieldRepository.save(f);
            return true;
        }
        return false;
    }
}
