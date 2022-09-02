package com.loginFireAndroid.demo.security;

import com.loginFireAndroid.demo.security.token.FirebaseEntryPoint;
import com.loginFireAndroid.demo.security.token.FirebaseFilter;
import com.loginFireAndroid.demo.security.token.FirebaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    FirebaseEntryPoint entryPoint;
    @Autowired
    FirebaseProvider provider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(entryPoint);
        http.addFilterBefore(new FirebaseFilter(), BasicAuthenticationFilter.class);
        http.authenticationProvider(provider);
        return http.build();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint);
        http.addFilterBefore(new FirebaseFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }*/

}
