package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.passwordcode.PasswordCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PasswordCodeController {
    private final PasswordCodeService passwordCodeService;

    // CREATE AND UPDATE
    @PostMapping("/password-code")
    public void createOrUpdatePasswordCode(@RequestBody PasswordCode passwordCode) {
        passwordCodeService.savePasswordCode(passwordCode);
    }

    // READ BY CODE
    @GetMapping("/password-code/{code}")
    public PasswordCode getPasswordCodeByCode(@PathVariable String code) {
        return passwordCodeService.getPasswordCodeByCode(code);
    }

    // READ BY USER
    @GetMapping("/password-code")
    public PasswordCode getPasswordCodeByUser(@RequestBody User user) {
        return passwordCodeService.getPasswordCodeByUser(user);
    }

    // READ ALL
    @GetMapping("/password-codes")
    public List<PasswordCode> getAllPasswordCodes() {
        return passwordCodeService.getPasswordCodes();
    }

    // DELETE BY CODE
    @DeleteMapping("/password-code/{code}")
    public void deletePasswordCodeByCode(@PathVariable String code) {
        passwordCodeService.deletePasswordCodeByCode(code);
    }

    // DELETE BY USER
    @DeleteMapping("/password-code")
    public void deletePasswordCodeByUser(@RequestBody User user) {
        passwordCodeService.deletePasswordCodeByUser(user);
    }
}
