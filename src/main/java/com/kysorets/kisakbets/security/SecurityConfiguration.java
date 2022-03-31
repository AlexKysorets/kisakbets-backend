package com.kysorets.kisakbets.security;

import com.kysorets.kisakbets.security.filters.CustomAuthenticationFilter;
import com.kysorets.kisakbets.security.filters.CustomAuthorizationFilter;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // PATHS FOR EVERYBODY
        http.authorizeRequests().antMatchers( "/login", "/token/refresh", "/signup", "/email/verify", "/forgot-pass/*",
                "/forgot-username/send", "/settings/email/change", "/stats/*", "/contact-us-message/save", "/news/subscribe").permitAll();
        // PATHS FOR ADMINS
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**")
                .hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**")
                .hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**")
                .hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/contact-us-message/send")
                .hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/news/create", "/news/send")
                .hasAnyAuthority("ROLE_ADMIN");
        // PATH FOR USERS
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/email/send")
                .hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/settings/*")
                .hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/daily-prediction/*")
                .hasAnyAuthority("ROLE_USER");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), userService));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
