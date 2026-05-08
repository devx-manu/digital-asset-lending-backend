package com.project.DigitalAssetLendingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.DigitalAssetLendingSystem.dto.LoginRequest;
import com.project.DigitalAssetLendingSystem.dto.LoginResponse;
import com.project.DigitalAssetLendingSystem.dto.RegisterRequest;
import com.project.DigitalAssetLendingSystem.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        service.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return service.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}