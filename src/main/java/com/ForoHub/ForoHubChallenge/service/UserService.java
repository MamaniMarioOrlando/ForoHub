package com.ForoHub.ForoHubChallenge.service;

import com.ForoHub.ForoHubChallenge.model.User.DataNewUser;
import com.ForoHub.ForoHubChallenge.model.User.IUserRepository;
import com.ForoHub.ForoHubChallenge.model.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserEntity registerNewUser(DataNewUser dataNewUser){
        UserEntity newUser = new UserEntity();
        newUser.setUsername(dataNewUser.username());
        newUser.setEmail(dataNewUser.email());
        newUser.setPassword(passwordEncoder.encode(dataNewUser.password()));

        return userRepository.save(newUser);
    }
}
