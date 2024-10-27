package com.pet.cafe.service.impl;

import com.pet.cafe.dto.JwtAuthenticationResponseDTO;
import com.pet.cafe.dto.SignInRequestDTO;
import com.pet.cafe.dto.SignUpRequestDTO;
import com.pet.cafe.entity.Role;
import com.pet.cafe.service.AuthenticationService;
import com.pet.cafe.service.JwtService;
import com.pet.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userService;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponseDTO signUp(String id, SignUpRequestDTO requestDTO) {
        var user = User.builder().password(passwordEncoder.encode(requestDTO.getPassword()))
                        .roles(String.valueOf(Role.ROLE_USER))
                                .username(requestDTO.getFirstName() + " " + requestDTO.getLastName())
                                        .build();
        userService.create((com.pet.cafe.entity.User) user);
        return new JwtAuthenticationResponseDTO(jwtService.generateToken(user));
    }

    @Override
    public JwtAuthenticationResponseDTO signIn(SignInRequestDTO requestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                requestDTO.getEmail(),
                requestDTO.getPassword()
        ));

        var user = userService.userDetailsService().loadUserByUsername(requestDTO.getEmail());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDTO(jwt);    }
}
