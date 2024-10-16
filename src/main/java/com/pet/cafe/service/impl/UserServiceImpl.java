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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository repository;
    UserMapper mapper;

    public List<UserDTO> getUsers() {
        return mapper.entitiesToDto(repository.findAll());
    }

    public UserDTO getUser(String id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        return mapper.entityToDto(user);
    }

    public void deleteUser(String id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        repository.delete(user);
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setFirstName(userDTO.firstName());
        user.setSecondName(userDTO.secondName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());

        repository.save(user);
    }

    @Override
    public void createUser(String id, UserDTO request) {
        User user = mapper.dtoToEntity(request, id);
        repository.save(user);
    }
}
