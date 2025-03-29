package com.example.DomguiaSimo_BankingApp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepo;

    @Override
    public void loginUser(String email, String password) {

    }

    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> Ou = userRepo.findById(id);
        if(Ou.isPresent()){
            return Ou.get();
        }
        return null;
    }

    @Override
    public void updateUser(Long id ,User new_user) {
        Optional<User> Ou = userRepo.findById(id);
        if(Ou.isPresent()){
            userRepo.deleteById(id);
            userRepo.save(new_user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
