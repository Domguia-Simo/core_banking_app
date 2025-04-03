package com.example.DomguiaSimo_BankingApp.User;

import com.example.DomguiaSimo_BankingApp.Config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserService implements UserServiceInterface , UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder bcrypt;
    public UserService() {
        bcrypt = new BCryptPasswordEncoder();
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> Ou = userRepo.findByEmail(username);
        if(Ou.isPresent()){
            User user = Ou.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getRole().name()) // Convert Role to authority
                    .build();
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public void loginUser(String email, String password) {
//        User u = new User();
//        AuthenticationManager authenticationManager = null;
//        Authentication authenticate = authenticationManager.authenticate(u, password);
    }

    @Override
    public void registerUser(User user) {
        String password = user.getPassword();
        password = bcrypt.encode(password);
        user.setPassword(password);
        user.setRole(Role.USER);
        userRepo.save(user);
    }

    public void registerAdmin(User user) {
        String password = user.getPassword();
        password = bcrypt.encode(password);
        user.setPassword(password);
        user.setRole(Role.ADMIN);
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
