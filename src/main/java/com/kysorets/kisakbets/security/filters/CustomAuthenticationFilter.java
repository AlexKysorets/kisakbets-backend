package com.kysorets.kisakbets.security.filters;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.security.token.Token;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // for x-www-form-urlencoded
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // for json
        UserInfo userInfo = null;
        try {
            userInfo = convertJsonToObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo.getUsername(),
                userInfo.getPassword());
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = new Token().giveAccessToken(user.getUsername(), request, new ArrayList<>(), user.getAuthorities());
        String refresh_token = new Token().giveRefreshToken(user.getUsername(), request);
        // get tokens in headers
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);

        // get tokens in json response
        com.kysorets.kisakbets.model.User userInfo = userService.getUserByUsername(user.getUsername());
        Map<String, Object> result = new HashMap<>();
        result.put("username", userInfo.getUsername());
        result.put("password", userInfo.getPassword());
        result.put("email", userInfo.getEmail());
        result.put("access_token", access_token);
        result.put("refresh_token", refresh_token);
        result.put("isVerified", userInfo.isVerified());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Bad credentials!");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(401);
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    public UserInfo convertJsonToObject(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        UserInfo userInfo = objectMapper.readValue(sb.toString(), UserInfo.class);
        return userInfo;
    }
}

@Data
class UserInfo {
    private String username;
    private String password;
}
