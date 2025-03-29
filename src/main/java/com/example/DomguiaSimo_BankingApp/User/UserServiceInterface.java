package com.example.DomguiaSimo_BankingApp.User;

import java.util.List;

public interface UserServiceInterface {
    public void loginUser(String email ,String password);
    public void registerUser(User user);
    public List<User> getUsers();
    public User getUser(Long id);
    public void updateUser(Long id ,User user);
    public void deleteUser(Long id);

}
