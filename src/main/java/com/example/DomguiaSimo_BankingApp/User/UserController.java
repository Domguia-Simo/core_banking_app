package com.example.DomguiaSimo_BankingApp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String,String> data){
        String email = data.get("email");
        String password = data.get("password");

        return ResponseEntity.ok("Login successfully");
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user){
        System.out.println(user);
        userService.registerUser(user);
        System.out.println("user registration");
        return ResponseEntity.ok("Registration successfully");
    }

    @GetMapping("/get-user/{id}")
    ResponseEntity<?> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/get-users")
    ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/delete-user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/update-user/{id}")
    ResponseEntity<?> updateUser(@PathVariable("id") Long id ,@RequestBody User user){
        userService.updateUser(id ,user);
        return ResponseEntity.ok("User updated successfully");
    }
}
