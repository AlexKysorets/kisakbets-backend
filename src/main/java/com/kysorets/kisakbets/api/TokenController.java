package com.kysorets.kisakbets.api;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.security.token.Token;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {
    private final UserService userService;

    @GetMapping("/refresh")
    public void refreshUserToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                DecodedJWT decodedJWT = new Token().verifyJWT(authorizationHeader);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByUsername(username);
                String access_token = new Token().giveAccessToken(username, request, user.getRoles(), null);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.sendError(FORBIDDEN.value());
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
