/*
package com.ninos.DB;

import com.ninos.model.User;
import com.ninos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Runner implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("ninos@yahoo.com");
        user.setPassword(passwordEncoder.encode("11111"));
        userRepository.save(user);

        User user1 = new User();
        user1.setEmail("nahrain@yahoo.com");
        user1.setPassword(passwordEncoder.encode("22222"));
        userRepository.save(user1);


    }
}
*/
