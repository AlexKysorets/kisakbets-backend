package com.kysorets.kisakbets.service.verificationcode;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VerificationCodeService {
    VerificationCode getVerificationCodeByCode(String code);
    VerificationCode getVerificationCodeByExpiresAt(LocalDateTime localDateTime);
    VerificationCode getVerificationCodeByUser(User user);
    List<VerificationCode> getVerificationCodes(int page, int size);
    void saveVerificationCode(VerificationCode verificationCode);
    void deleteVerificationCodeByCode(String code);
    void deleteVerificationCodeByUser(User user);
}
