package com.project.DigitalAssetLendingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.DigitalAssetLendingSystem.dto.LoginResponse;
import com.project.DigitalAssetLendingSystem.dto.RegisterRequest;
import com.project.DigitalAssetLendingSystem.entity.User;
import com.project.DigitalAssetLendingSystem.exception.BadRequestException;
import com.project.DigitalAssetLendingSystem.exception.ResourceNotFoundException;
import com.project.DigitalAssetLendingSystem.exception.UnauthorizedException;
import com.project.DigitalAssetLendingSystem.repository.UserRepository;
import com.project.DigitalAssetLendingSystem.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwt;

    public void register(RegisterRequest request) {

        if (repo.findByEmail(request.getEmail()).isPresent()) {

            throw new BadRequestException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setRole(request.getRole());

        user.setPassword(
                encoder.encode(request.getPassword())
        );

        repo.save(user);
    }

    public LoginResponse login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        if (!encoder.matches(password, user.getPassword())) {

            throw new UnauthorizedException(
                    "Invalid credentials"
            );
        }

        String token = jwt.generateToken(email);

        return new LoginResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getDepartment()
        );
    }
}