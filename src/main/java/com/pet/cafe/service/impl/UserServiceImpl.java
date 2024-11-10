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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository repository;
    UserMapper mapper;

    public List<UserDTO> getUsers() {
        log.debug("Starting getUsers method.");
        var users = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved users.");
        log.debug("Leaving getUsers method.");
        return users;
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
        user.setPassportId(userDTO.passportId());
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

    @Override
    public UserDTO createUser(UserDTO request) {
        log.debug("Starting createUser method.");
        User user = mapper.dtoToEntity(request);
        var createdUser = mapper.entityToDto(repository.save(user));
        log.info("Successfully created user.");
        log.debug("Leaving createUser method.");
        return createdUser;
    }
}