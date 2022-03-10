package com.kysorets.kisakbets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailSenderConfiguration {
    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
