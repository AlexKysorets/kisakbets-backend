package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot-pass")
public class ForgotPasswordController {
    private final UserService userService;

    @PostMapping("/send")
    public void forgotPassword(@RequestBody ForgotPassInfo info) {
        User user = userService.getUserByEmail(info.getEmail());
        if (user != null) {
            
        } else {
            // such user doesn't exist
        }
    }
}

@Data
class ForgotPassInfo {
    private String email;
}