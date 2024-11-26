package com.pet.cafe.service.impl;


import com.pet.cafe.dto.LoginUserDTO;
import com.pet.cafe.dto.RegisterUserDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserServiceImpl userServiceImpl, UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDTO input) {


        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setEmail(input.getEmail());
        user.setLastName(input.getLastName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setPhoneNumber(input.getPhone_number());
        user.setSecondName(input.getSecondName());

        return userRepository.save(userServiceImpl.create(user));
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}