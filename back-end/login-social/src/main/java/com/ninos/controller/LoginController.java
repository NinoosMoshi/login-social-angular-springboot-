package com.ninos.controller;

import com.ninos.dto.JwtLogin;
import com.ninos.dto.LoginResponse;
import com.ninos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

   private TokenService tokenService;

   @Autowired
    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody JwtLogin jwtLogin) throws Exception {
        return tokenService.login(jwtLogin);
    }






}
