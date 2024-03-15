package com.example.validatingforminput.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    public void makeCryotedPassword (User user){
        String hashsetPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setRole("ROLE_USER");
        user.setPassword(hashsetPassword);
        userRepository.save(user);
    }

}
