package com.example.DomguiaSimo_BankingApp.User;

import com.example.DomguiaSimo_BankingApp.Config.JWTServices;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTServices jwtService;

    @Operation(summary = "Account authentication endpoint")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        System.out.println("In the user login controller");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        String token = jwtService.generateToken(user);
        System.out.println(token);
        return ResponseEntity.ok(token);
//
    }

    @Operation(summary = "Account creation")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user , BindingResult bindingResult){
        System.out.println("User registration");
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField() ,error.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("errors" ,errors) , HttpStatus.BAD_REQUEST);
        }
        System.out.println(user);
        userService.registerUser(user);
        System.out.println("user registration");
        return ResponseEntity.ok("Registration successfully");
    }

//    Admin controllers
//    Admin registration
    @PostMapping("/register-admin")
    ResponseEntity<?> registerAdmin(@Valid @RequestBody User user , BindingResult bindingResult){
        System.out.println("Admin registration");
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField() ,error.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("errors" ,errors) , HttpStatus.BAD_REQUEST);
        }
        System.out.println(user);
        userService.registerAdmin(user);
        System.out.println("Admin registration");
        return ResponseEntity.ok("Registration successfully");
    }

    @Operation(summary = "Getting a user")
    @GetMapping("/get-user/{id}")
    ResponseEntity<?> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Operation(summary = "Getting the list of users")
    @GetMapping("/get-users")
    ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @Operation(summary = "Deleting a user")
    @DeleteMapping("/delete-user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Operation(summary = "Updating a user")
    @PutMapping("/update-user/{id}")
    ResponseEntity<?> updateUser(@PathVariable("id") Long id ,@Valid @RequestBody User user ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String ,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> errors.put(e.getField() ,e.getDefaultMessage()));
            return new ResponseEntity<>(Map.of("errors",errors) ,HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(id ,user);
        return ResponseEntity.ok("User updated successfully");
    }
}
