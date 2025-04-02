package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
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

    @Operation(summary = "Field creation for an operation endpoint" ,responses = {
            @ApiResponse(responseCode = "201" ,description = "Field created" ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401" ,description = "Invalid input data")
    })

    @PostMapping("/create-field/{operation_id}")
    ResponseEntity<?> createField(@PathVariable("operation_id") Long id, @Valid @RequestBody List<Field> field , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("error", errors) , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fieldService.createField(field ,id) ,HttpStatus.CREATED);
    }

    @Operation(summary = "Getting the list of created fields")
    @GetMapping("/get-fields")
    ResponseEntity<?> getFields(){
        return new ResponseEntity<>(fieldService.getFields() ,HttpStatus.OK);
    }

    @Operation(summary = "Getting a field from its id")
    @GetMapping("/get-field/{id}")
    ResponseEntity<?> getField(@PathVariable("id") Long id){
        return new ResponseEntity<>(fieldService.getField(id) ,HttpStatus.OK);
    }

    @Operation(summary = "Deleting a field")
    @DeleteMapping("/delete-field/{id}")
    ResponseEntity<?> deleteField(@PathVariable("id") Long id){
        Boolean result = fieldService.deleteField(id);
        if(result){
            return new ResponseEntity<>("Field deleted successfully" ,HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error" ,"Invalid field id") ,HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "To update a field" ,responses = {
            @ApiResponse(responseCode = "200" ,description = "Field correctly updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input date")
    })
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
