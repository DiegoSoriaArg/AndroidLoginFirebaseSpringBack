package com.loginFireAndroid.demo.controller;

import com.loginFireAndroid.demo.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<MessageDto> getHello(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(new MessageDto("Hola " + userName + " desde el BackEnd de SpringBoot"));
    }

}
