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

    public UserDTO getUser(long id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        return mapper.entityToDto(user);
    }

    public void deleteUser(long id) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        repository.delete(user);
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        var user = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setPassportId(userDTO.passportId());
        user.setFirstName(userDTO.firstName());
        user.setSecondName(userDTO.secondName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());

        return mapper.entityToDto(repository.save(user));
    }

    @Override
    public UserDTO createUser(UserDTO request) {
        User user = mapper.dtoToEntity(request);
        return mapper.entityToDto(repository.save(user));
    }
}
