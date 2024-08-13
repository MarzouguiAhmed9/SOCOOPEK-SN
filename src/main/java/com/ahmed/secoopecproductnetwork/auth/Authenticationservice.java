package com.ahmed.secoopecproductnetwork.auth;

import com.ahmed.secoopecproductnetwork.email.EmailService;
import com.ahmed.secoopecproductnetwork.email.EmailTemplateName;
import com.ahmed.secoopecproductnetwork.role.RoleRepository;
import com.ahmed.secoopecproductnetwork.user.Token;
import com.ahmed.secoopecproductnetwork.user.TokenRepository;
import com.ahmed.secoopecproductnetwork.user.User;
import com.ahmed.secoopecproductnetwork.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl; // Correct placement of @Value
    TokenRepository tokenRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;
    public void register(RegistrationRequest request) throws MessagingException {
        //role is an independant entity
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

//build a user with the registration request

    }

    private void sendvalidationemail(User user) throws MessagingException {
        var newtoken=generateandsaveactivationtoken(user);

        String activationurl;
        emailService.sendemail(user.getEmail(), user.getFirstname(), EmailTemplateName.ACTIVATE_ACCOUNT,activationUrl,newtoken,"account activation");
    }

    private String generateandsaveactivationtoken(User user) {
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
