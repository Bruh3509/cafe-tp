package com.pet.cafe.controller;

import com.pet.cafe.dto.LoginResponseDTO;
import com.pet.cafe.dto.LoginUserDTO;
import com.pet.cafe.dto.RegisterUserDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.service.impl.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
private final UserServiceImpl userService;
    public AuthenticationController(JwtService jwtService, EmailVerificationServiceImpl emailVerificationService, AuthenticationService authenticationService, EmailService emailService, UserServiceImpl userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        String token = registeredUser.getEmailVerificationToken();
        //TO-DO sending
        emailService.sendVerificationEmail(registerUserDto.getEmail(), token);
        return ResponseEntity.ok(/*registeredUser.toString() +*/ "User registered. Verify email!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}