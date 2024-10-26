package com.pet.cafe.service;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUser(long id);

    void deleteUser(long id);

    UserDTO updateUser(long id, UserDTO userDTO);

    UserDTO createUser(UserDTO userDTO);
}
