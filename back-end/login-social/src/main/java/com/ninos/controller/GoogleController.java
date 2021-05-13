package com.ninos.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import com.ninos.dto.TokenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class GoogleController {

    @Value("${google.id}")
    private String idClient;


    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws IOException {

        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();

        // get google id
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport,factory)
                                                                .setAudience(Collections.singleton(idClient));

        // get google token
        GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getToken());

        // carry the token
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }



}
