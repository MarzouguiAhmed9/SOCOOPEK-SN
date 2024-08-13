package com.ahmed.secoopecproductnetwork.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@Service

public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sendemail(String to,String username,EmailTemplateName emailTemplateName,String confimationurl,String activationcode,String subject) throws MessagingException {
        String templatename;
        if(emailTemplateName==null) {
            templatename = "confim name";
        }
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String,Object> properties=new HashMap<>();
        properties.put("username",username);
        properties.put("confirmationUrl",confimationurl);
        properties.put("activation_code",activationcode);
        Context context=new Context();
        context.setVariables(properties);
        helper.setFrom("contact@aliboucoding.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String template =templateEngine.process(String.valueOf(emailTemplateName),context);
        helper.setText(template,true);
        mailSender.send(mimeMessage);

    }
}
