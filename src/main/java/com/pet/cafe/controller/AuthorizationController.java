package com.pet.cafe.controller;

import com.pet.cafe.dto.JwtAuthenticationResponseDTO;
import com.pet.cafe.dto.SignInRequestDTO;
import com.pet.cafe.dto.SignUpRequestDTO;
import com.pet.cafe.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDTO signUp(@RequestBody @Valid SignUpRequestDTO request) {
        return authenticationService.signUp("1", request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDTO signIn(@RequestBody @Valid SignInRequestDTO request) {
        return authenticationService.signIn(request);
    }
}