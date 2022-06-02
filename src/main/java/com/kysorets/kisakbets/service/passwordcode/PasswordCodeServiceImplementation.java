package com.kysorets.kisakbets.service.passwordcode;

import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.repository.PasswordCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordCodeServiceImplementation implements PasswordCodeService{
    private final PasswordCodeRepository passwordCodeRepository;

    @Override
    public PasswordCode getPasswordCodeByCode(String code) {
        return passwordCodeRepository.getByCode(code);
    }

    @Override
    public PasswordCode getPasswordCodeByExpiresAt(LocalDateTime date) {
        return passwordCodeRepository.getByExpiresAt(date);
    }

    @Override
    public PasswordCode getPasswordCodeByUser(User user) {
        return passwordCodeRepository.getByUser(user);
    }

    @Override
    public List<PasswordCode> getPasswordCodes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var passwordCodes = passwordCodeRepository.findAll(pageable);
        return passwordCodes.toList();
    }

    @Override
    public void savePasswordCode(PasswordCode passwordCode) {
        passwordCodeRepository.save(passwordCode);
    }

    @Override
    public void deletePasswordCodeByUser(User user) {
        passwordCodeRepository.deleteByUser(user);
    }

    @Override
    public void deletePasswordCodeByCode(String code) {
        passwordCodeRepository.deleteByCode(code);
    }
}
