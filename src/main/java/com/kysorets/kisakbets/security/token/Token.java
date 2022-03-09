package com.kysorets.kisakbets.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kysorets.kisakbets.model.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class Token {
    public DecodedJWT verifyJWT (String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }

    public String giveAccessToken(String username, HttpServletRequest request, Collection<Role> roles,
                                  Collection<GrantedAuthority> grantedAuthorities) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        if (!roles.isEmpty()) {
            return JWT.create()
                    .withSubject(username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 6 * 60 * 60 * 1000)) // 6 hours
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", roles.stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
        } else {
            return JWT.create()
                    .withSubject(username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 6 * 60 * 60 * 1000)) // 6 hours
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
        }
    }

    public String giveRefreshToken(String username, HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)) // 2 weeks
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }
}
