package com.ahmed.secoopecproductnetwork.auth;

import com.ahmed.secoopecproductnetwork.email.EmailService;
import com.ahmed.secoopecproductnetwork.role.RoleRepository;
import com.ahmed.secoopecproductnetwork.user.Token;
import com.ahmed.secoopecproductnetwork.user.TokenRepository;
import com.ahmed.secoopecproductnetwork.user.User;
import com.ahmed.secoopecproductnetwork.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Authenticationservice {
    private RoleRepository roleRepository;
    UserRepository userRepository;
    TokenRepository tokenRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;
    public void register(RegistrationRequest request) {
        var userrole=roleRepository.findByName("USER").orElseThrow(()->new IllegalStateException("not found"));
         var user= User.builder().firstname(request.getFirstname())
                 .lastname(request.getLastname())
                 .email(request.getEmail())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .accountLocked(false)
                 .enabled(false)
                 .roles(List.of(userrole)).build();
         userRepository.save(user);
         sendvalidationemail(user);



    }

    private void sendvalidationemail(User user) {
        var newtoken=generateandsaceactivationtoken(user);
    }

    private String generateandsaceactivationtoken(User user) {
        //generate token
        String generatedToken=generateActivationtoken(6);
        var token= Token.builder().token(generatedToken)
                .createdat(LocalDateTime.now())
                .expiresat(LocalDateTime.now().plusMinutes(15))
                .user(user).build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationtoken(int length) {
        String chracters="0123456789";
        StringBuilder codebuilder=new StringBuilder();
        SecureRandom secureRandom=new SecureRandom();
        for(int i=0;i<length;i++){int randomindex=secureRandom.nextInt(chracters.length());
            codebuilder.append(chracters.charAt(randomindex));
    }
    return codebuilder.toString();

}}
