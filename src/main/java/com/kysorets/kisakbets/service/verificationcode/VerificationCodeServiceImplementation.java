package com.kysorets.kisakbets.service.verificationcode;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import com.kysorets.kisakbets.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImplementation implements VerificationCodeService{
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public VerificationCode getVerificationCodeByCode(String code) {
        return verificationCodeRepository.getByCode(code);
    }

    @Override
    public VerificationCode getVerificationCodeByExpiresAt(LocalDateTime localDateTime) {
        return verificationCodeRepository.getByExpiresAt(localDateTime);
    }

    @Override
    public VerificationCode getVerificationCodeByUser(User user) {
        return verificationCodeRepository.getByUser(user);
    }

    @Override
    public List<VerificationCode> getVerificationCodes() {
        return verificationCodeRepository.findAll();
    }

    @Override
    public void saveVerificationCode(VerificationCode verificationCode) {
        verificationCodeRepository.save(verificationCode);
    }

    @Override
    public void deleteVerificationCodeByCode(String code) {
        verificationCodeRepository.deleteByCode(code);
    }

    @Override
    public void deleteVerificationCodeByUser(User user) {
        verificationCodeRepository.deleteByUser(user);
    }
}
