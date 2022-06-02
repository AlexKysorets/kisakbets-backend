package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import com.kysorets.kisakbets.service.verificationcode.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VerificationCodeController {
    private final VerificationCodeService verificationCodeService;

    // CREATE
    @PostMapping("/code")
    public void createVerificationCode(@RequestBody VerificationCode verificationCode) {
        verificationCodeService.saveVerificationCode(verificationCode);
    }

    // UPDATE
    @PutMapping("/code")
    public void updateVerificationCode(@RequestBody VerificationCode verificationCode) {
        verificationCodeService.saveVerificationCode(verificationCode);
    }

    // READ BY CODE
    @GetMapping("/code/{code}")
    public ResponseEntity<VerificationCode> getVerificationCodeByCode(@PathVariable String code) {
        return ResponseEntity.ok(verificationCodeService.getVerificationCodeByCode(code));
    }

    // READ BY USER
    @GetMapping("/code")
    public ResponseEntity<VerificationCode> getVerificationCodeByUser(@RequestBody User user) {
        return ResponseEntity.ok(verificationCodeService.getVerificationCodeByUser(user));
    }

    // READ ALL
    @GetMapping("/codes")
    public ResponseEntity<List<VerificationCode>> getAllVerificationCodes(@RequestParam(required = false, defaultValue = "0") int page,
                                                                         @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(verificationCodeService.getVerificationCodes(page, size));
    }

    // DELETE BY CODE
    @DeleteMapping("/code/{code}")
    public void deleteVerificationCodeByCode(@PathVariable String code) {
        verificationCodeService.deleteVerificationCodeByCode(code);
    }

    // DELETE BY USER
    @DeleteMapping("/code")
    public void deleteVerificationCodeByUser(@RequestBody User user) {
        verificationCodeService.deleteVerificationCodeByUser(user);
    }
}
