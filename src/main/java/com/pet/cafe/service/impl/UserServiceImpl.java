package com.pet.cafe.service.impl;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.mapstruct.SocketSessionMapper;
import com.pet.cafe.mapstruct.UserMapper;
import com.pet.cafe.repository.SocketSessionRepository;
import com.pet.cafe.repository.UserRepository;

import com.pet.cafe.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository repository;
    SocketSessionRepository sessionRepository;
    UserMapper mapper;
    SocketSessionMapper sessionMapper;

    public List<User> allUsers(){
        return new ArrayList<>(repository.findAll());
    }
    public List<UserDTO> getUsers() {
        log.debug("Starting getUsers method.");
        var users = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved users.");
        log.debug("Leaving getUsers method.");
        return users;
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
      
        log.info("Successfully retrieved user with id {}.");
        log.debug("Leaving getUser method.");
        return mapper.entityToDto(user);
    }

  public UserDTO getUser(long id) {
        log.debug("Starting getUser method with id {}.", id);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("User with id {} not found!", id);
                    return new EntityNotFoundException("User not found!");
                });
    
        log.info("Successfully retrieved user with id {}.", id);
        log.debug("Leaving getUser method.");
        return mapper.entityToDto(user);
    }

public void deleteUser(long id) {
        log.debug("Starting deleteUser method with id {}.", id);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete user with non-existent id {}.", id);
                    return new EntityNotFoundException("User not found!");
                });

        repository.delete(user);
        log.info("Successfully removed user with id {}.", id);
        log.debug("Leaving deleteUser method.");
    }
  
  public void deleteUser(String email) {
        var user = repository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        repository.delete(user);
    }

@Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        log.debug("Starting updateUser method with id {}.", id);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to update user with non-existent id {}.", id);
                    return new EntityNotFoundException("User not found!");
                });

        log.debug("Updating user fields with new values.");
        user.setFirstName(userDTO.firstName());
        user.setSecondName(userDTO.secondName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());

        var updatedUser =  mapper.entityToDto(repository.save(user));
        log.info("Successfully updated user with id {}.", id);
        log.debug("Leaving updateUser method.");
        return updatedUser;
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

    @Override
    public UserDTO createUser(UserDTO request) {
        log.debug("Starting createUser method.");
        User user = mapper.dtoToEntity(request);
        var createdUser = mapper.entityToDto(repository.save(user));
        log.info("Successfully created user.");
        log.debug("Leaving createUser method.");
        return createdUser;
    }

    @Override
    public Set<SocketSessionDTO> getSessions(long id) {
        log.debug("Starting getConnections method with id {}.", id);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("User with id {} not found!", id);
                    return new EntityNotFoundException("User not found!");
                });

        var connections = sessionMapper.entitiesToDto(user.getSessions());
        log.debug("Leaving getConnections method.");
        return connections;
    }

    @Override
    public void addSession(long userId, String sessionId) {
        var session = sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> {
                    log.error("Session with id {} not found!", sessionId);
                    return new EntityNotFoundException("Session not found!");
                });

        var user = repository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("User with id {} not found!", userId);
                    return new EntityNotFoundException("User not found!");
                });

        user.getSessions().add(session);
        session.setUser(user);
    }

    @Override
    public void deleteSession(long userId, String sessionId) {
        var session = sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> {
                    log.error("Session with id {} not found!", sessionId);
                    return new EntityNotFoundException("Session not found!");
                });

        var user = repository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("User with id {} not found!", userId);
                    return new EntityNotFoundException("User not found!");
                });

        user.getSessions().remove(session);
        session.setUser(null);
    }
  
    public void createUser(String id, UserDTO request) {
        User user = mapper.dtoToEntity(request);
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