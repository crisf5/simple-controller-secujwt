package com.cbatech.springsecu.auth.controller;

import com.cbatech.springsecu.auth.dto.JwtResponseDto;
import com.cbatech.springsecu.auth.dto.LoginDto;
import com.cbatech.springsecu.auth.dto.RegisterDto;
import com.cbatech.springsecu.auth.service.LoginService;
import com.cbatech.springsecu.auth.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private LoginService loginService;
    private RegisterService registerService;

    public AuthController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto){
        String token = loginService.login(loginDto);
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setAccessToken(token);

        return new ResponseEntity<>(jwtResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String msg = registerService.register(registerDto);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
