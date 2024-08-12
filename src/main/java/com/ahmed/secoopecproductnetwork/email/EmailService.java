package com.ahmed.secoopecproductnetwork.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    public void sendemail(String to,String username,EmailTemplateName emailTemplateName,String confimationurl,String activationcode,String subject){

    }
}
