package com.cisco.orderapp.security.api;

import com.cisco.orderapp.security.dto.JwtTokenResponse;
import com.cisco.orderapp.security.dto.SignInRequest;
import com.cisco.orderapp.security.dto.SignUpRequest;
import com.cisco.orderapp.security.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationService service;

    @PostMapping("/register")
    public String register(@RequestBody SignUpRequest request) {
        System.out.println("Entered!!!");
        service.signup(request);
        return "Registered!!!";
    }

    @PostMapping("/login")
    public String login(@RequestBody SignInRequest request) {
        return service.signIn(request);
    }
}
