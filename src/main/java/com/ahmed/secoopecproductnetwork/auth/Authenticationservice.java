package com.ahmed.secoopecproductnetwork.auth;

import com.ahmed.secoopecproductnetwork.email.EmailService;
import com.ahmed.secoopecproductnetwork.email.EmailTemplateName;
import com.ahmed.secoopecproductnetwork.role.RoleRepository;
import com.ahmed.secoopecproductnetwork.security.JWTService;
import com.ahmed.secoopecproductnetwork.user.Token;
import com.ahmed.secoopecproductnetwork.user.TokenRepository;
import com.ahmed.secoopecproductnetwork.user.User;
import com.ahmed.secoopecproductnetwork.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class Authenticationservice {
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;
   private final UserRepository userRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private  String activationUrl; // Correct placement of @Value
    private final TokenRepository tokenRepository;

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
     String charat="123456789";
     StringBuilder token=new StringBuilder();
     SecureRandom randomin=new SecureRandom();
     for (int i=0;i<length;i++){
        int k=randomin.nextInt(charat.length());
        token.append(charat.charAt(k));
     }
     return token.toString();

}

    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        //MAKE AUTHENTICATED ENTITE FROM UPA that get data from request
        var auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(),authenticateRequest.getPassword()));
        var cliams=new HashMap<String, Object>();
        // Get the authenticated user details

        var user=((User)auth.getPrincipal());
        cliams.put("name",user.getName());
        var jwtoken=jwtService.generatetoken(user,cliams);
        return AuthenticateResponse.builder().token(jwtoken).build();
    }
    @Transactional
    public void activateaccount(String token) throws MessagingException {
       Token savedtoken=tokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("invalid token"));
       if(LocalDateTime.now().isAfter(savedtoken.getExpiresat())){
           sendvalidationemail(savedtoken.getUser());
           throw  new RuntimeException("activation token has expired");
    }
       var user=userRepository.findById(savedtoken.getUser().getId()).orElseThrow(()->new UsernameNotFoundException("not found"));
       userRepository.save(user);
       savedtoken.setValidateat(LocalDateTime.now());
       tokenRepository.save(savedtoken);

    }


}
