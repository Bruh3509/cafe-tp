package com.pet.cafe.service;

import com.pet.cafe.dto.JwtAuthenticationResponseDTO;
import com.pet.cafe.dto.SignInRequestDTO;
import com.pet.cafe.dto.SignUpRequestDTO;

public interface AuthenticationService {
    JwtAuthenticationResponseDTO signUp(String id, SignUpRequestDTO requestDTO);
    JwtAuthenticationResponseDTO signIn(SignInRequestDTO requestDTO);
}
