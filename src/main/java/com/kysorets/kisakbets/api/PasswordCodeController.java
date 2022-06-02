package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.passwordcode.PasswordCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PasswordCodeController {
    private final PasswordCodeService passwordCodeService;

    // CREATE
    @PostMapping("/password-code")
    public void createPasswordCode(@RequestBody PasswordCode passwordCode) {
        passwordCodeService.savePasswordCode(passwordCode);
    }

    // UPDATE
    @PutMapping("/password-code")
    public void updatePasswordCode(@RequestBody PasswordCode passwordCode) {
        passwordCodeService.savePasswordCode(passwordCode);
    }

    // READ BY CODE
    @GetMapping("/password-code/{code}")
    public ResponseEntity<PasswordCode> getPasswordCodeByCode(@PathVariable String code) {
        return ResponseEntity.ok(passwordCodeService.getPasswordCodeByCode(code));
    }

    // READ BY USER
    @GetMapping("/password-code")
    public ResponseEntity<PasswordCode> getPasswordCodeByUser(@RequestBody User user) {
        return ResponseEntity.ok(passwordCodeService.getPasswordCodeByUser(user));
    }

    // READ ALL
    @GetMapping("/password-codes")
    public ResponseEntity<List<PasswordCode>> getAllPasswordCodes(@RequestParam(required = false, defaultValue = "0") int page,
                                                                  @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(passwordCodeService.getPasswordCodes(page, size));
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
