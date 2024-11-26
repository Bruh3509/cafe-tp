package com.pet.cafe.service.impl;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.mapstruct.UserMapper;
import com.pet.cafe.repository.UserRepository;

import com.pet.cafe.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    UserMapper mapper;

    public List<User> allUsers(){
        return new ArrayList<>(repository.findAll());
    }
    public List<UserDTO> getUsers() {
        return mapper.entitiesToDto(repository.findAll());
    }
//
//    @Deprecated
//    public void getAdmin() {
//        var user = getCurrentUser();
//        user.setRole(Role.ROLE_ADMIN);
//        repository.save(user);
//    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        return repository.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserDTO getUser(String email) {
        var user = repository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        return mapper.entityToDto(user);
    }

    public void deleteUser(String email) {
        var user = repository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        repository.delete(user);
    }

    public void updateUser(String email, UserDTO userDTO) {
        var user = repository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setFirstName(userDTO.firstName());
        user.setSecondName(userDTO.secondName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());

        repository.save(user);
    }

    public void createUser(String id, UserDTO request) {
        User user = mapper.dtoToEntity(request, id);
        repository.save(user);
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}