package com.ahmed.secoopecproductnetwork.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthenticationController {
    private final Authenticationservice authenticationservice;

 @PostMapping("/register")
 @ResponseStatus(HttpStatus.ACCEPTED)//202  dealing with asynchronous operations
 public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
     authenticationservice.register(registrationRequest);
     return ResponseEntity.accepted().build();
 }


    @PostMapping("/authenticate") //200
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody @Valid AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(authenticationservice.authenticate(authenticateRequest));
    }


   @GetMapping("/activate-account")
    void confirm(@RequestParam String token) throws MessagingException {
        authenticationservice.activateaccount(token);
    }


}