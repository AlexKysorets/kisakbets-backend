package com.kysorets.kisakbets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfiguration {
    @Bean
    JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("alexproba140920@gmail.com");
        javaMailSender.setPassword("2001rcTYSeYuca2001");
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }

    @Bean
    EmailSender emailSender() {
        return new EmailSender(javaMailSender());
    }
}
