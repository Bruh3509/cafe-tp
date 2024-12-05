package com.pet.cafe.service;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<UserDTO> getUsers();
//
//    @Deprecated
//    public void getAdmin() {
//        var user = getCurrentUser();
//        user.setRole(Role.ROLE_ADMIN);
//        repository.save(user);
//    }

    User create(User user);

    UserDetailsService userDetailsService();
    UserDTO getUser(String email);

    void deleteUser(String email);
    void updateUser(String email, UserDTO userDTO);

    void createUser(String id, UserDTO request);

    User getCurrentUser();

    User getByUsername(String username);
}