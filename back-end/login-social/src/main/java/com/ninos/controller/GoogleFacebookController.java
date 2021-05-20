package com.ninos.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.ninos.dto.JwtLogin;
import com.ninos.dto.LoginResponse;
import com.ninos.dto.TokenDTO;
import com.ninos.model.Role;
import com.ninos.service.RoleService;
import com.ninos.service.TokenService;
import com.ninos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/social")
@CrossOrigin("http://localhost:4200")
public class GoogleFacebookController {


    private UserService userService;
    private RoleService roleService;
    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    @Value("${mySecret.password}")
    private String password;

    @Autowired
    public GoogleFacebookController(UserService userService, RoleService roleService, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${google.id}")
    private String idClient;


    @PostMapping("/google")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws Exception {

        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();

        // get google id
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport,factory)
                                                                .setAudience(Collections.singleton(idClient));

        // get google token
        GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getToken());

        // carry the token
        GoogleIdToken.Payload payload = googleIdToken.getPayload();   // it's contain user's data(name,email,picture,...)

        String email = payload.getEmail();
        com.ninos.model.User user = new com.ninos.model.User();
        if(userService.ifEmailExist(email)){
            user = userService.getUserByEmail(email);
            System.out.println("===> Email is Exist");
        }else{
            user = createUser(email);
            System.out.println("===> Email is NOT Exist");
        }

        ////////////////
        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);

        ///////////////
        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }

    private com.ninos.model.User createUser(String email) {
        com.ninos.model.User user = new com.ninos.model.User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = roleService.getRoles();
        user.getRoles().add(roles.get(0));
        return userService.saveUser(user);

    }


    @PostMapping("/facebook")
    public ResponseEntity<LoginResponse> loginWithFacebook(@RequestBody TokenDTO tokenDTO) throws Exception {
        Facebook facebook = new FacebookTemplate(tokenDTO.getToken());
        String data[] = {"email"};
        User user = facebook.fetchObject("me", User.class,data);

        String email = user.getEmail();
        com.ninos.model.User userFace = new com.ninos.model.User();
        if(userService.ifEmailExist(email)){
            userFace = userService.getUserByEmail(email);
            System.out.println("===> Email is Exist");
        }else{
            userFace = createUser(email);
            System.out.println("===> Email is NOT Exist");
        }

        ////////////////
        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);

        ///////////////
        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }



}
