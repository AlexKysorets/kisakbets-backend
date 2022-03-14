package com.kysorets.kisakbets.service.passwordcode;

import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PasswordCodeService {
    PasswordCode getPasswordCodeByCode(String code);
    PasswordCode getPasswordCodeByExpiresAt(LocalDateTime date);
    PasswordCode getPasswordCodeByUser(User user);
    List<PasswordCode> getPasswordCodes();
    void savePasswordCode(PasswordCode passwordCode);
    void deletePasswordCodeByUser(User user);
    void deletePasswordCodeByCode(String code);
}
