package com.pet.cafe.service;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUser(String id);

    void deleteUser(String id);

    void updateUser(String id, UserDTO userDTO);

    void createUser(String id, UserDTO userDTO);

    User create(User user);
}