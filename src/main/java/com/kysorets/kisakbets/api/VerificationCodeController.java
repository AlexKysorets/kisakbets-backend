package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import com.kysorets.kisakbets.service.verificationcode.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VerificationCodeController {
    private final VerificationCodeService verificationCodeService;

    // CREATE AND UPDATE
    @PostMapping("/code")
    public void createOrUpdateVerificationCode(@RequestBody VerificationCode verificationCode) {
        verificationCodeService.saveVerificationCode(verificationCode);
    }

    // READ BY CODE
    @GetMapping("/code/{code}")
    public VerificationCode getVerificationCodeByCode(@PathVariable String code) {
        return verificationCodeService.getVerificationCodeByCode(code);
    }

    // READ BY USER
    @GetMapping("/code")
    public VerificationCode getVerificationCodeByUser(@RequestBody User user) {
        return verificationCodeService.getVerificationCodeByUser(user);
    }

    // READ ALL
    @GetMapping("/codes")
    public List<VerificationCode> getAllVerificationCodes() {
        return verificationCodeService.getVerificationCodes();
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
