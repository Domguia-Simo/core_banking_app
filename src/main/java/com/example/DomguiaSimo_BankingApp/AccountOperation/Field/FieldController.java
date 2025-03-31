package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import com.example.DomguiaSimo_BankingApp.AccountOperation.Operation.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping("/create-field/{operation_id}")
    ResponseEntity<?> createField(@PathVariable("operation_id") Long id, @Valid @RequestBody List<Field> field , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error", errors) , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fieldService.createField(field ,id) ,HttpStatus.CREATED);
    }

    @GetMapping("/get-fields")
    ResponseEntity<?> getFields(){
        return new ResponseEntity<>(fieldService.getFields() ,HttpStatus.OK);
    }

    @GetMapping("/get-field/{id}")
    ResponseEntity<?> getField(@PathVariable("id") Long id){
        return new ResponseEntity<>(fieldService.getField(id) ,HttpStatus.OK);
    }

    @DeleteMapping("/delete-field/{id}")
    ResponseEntity<?> deleteField(@PathVariable("id") Long id){
        Boolean result = fieldService.deleteField(id);
        if(result){
            return new ResponseEntity<>("Field deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid field id") ,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-field/{id}")
    ResponseEntity<?> updateField(@PathVariable("id") Long id, @Valid @RequestBody Field field, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error" ,errors) ,HttpStatus.BAD_REQUEST);
        }
        Boolean result = fieldService.updateField(id, field);
        if(result){
            return new ResponseEntity<>("Field updated successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid field id") ,HttpStatus.BAD_REQUEST);
    }

}
