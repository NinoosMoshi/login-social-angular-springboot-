package com.ninos.service;

import com.ninos.model.User;
import com.ninos.model.UserPrincipal;
import com.ninos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDetails loadByEmail(String email){
      User user = userRepository.findUserByEmail(email);
      UserPrincipal userPrincipal = new UserPrincipal(user);
      return userPrincipal;
    }







}
