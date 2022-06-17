package com.example.FootbalLeague.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.FootbalLeague.Model.Role.*;

@Configuration
@EnableWebSecurity
@Profile("!nosecurity")
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

    PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/**").permitAll()//.hasAuthority(UserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/**").permitAll()//.hasAuthority(UserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/**").permitAll()//.hasAuthority(UserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/**").permitAll()//hasAnyRole(PLAYER.name(),ADMIN.name(),MODERATOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails player = User.builder()
                .username("Dennis")
                .password(passwordEncoder.encode("password"))
                //.roles(PLAYER.name())
                .authorities(PLAYER.getGrantedAuthority())
                .build();

        UserDetails admin = User.builder()
                .username("Dominic")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthority())
                .build();

        return new InMemoryUserDetailsManager(
                player,
                admin
        );
    }

}
