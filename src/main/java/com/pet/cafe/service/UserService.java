package com.pet.cafe.service;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

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

    UserDTO getUser(long id);

    void deleteUser(long id);

    UserDTO updateUser(long id, UserDTO userDTO);

    UserDTO createUser(UserDTO userDTO);

    Set<SocketSessionDTO> getSessions(long id);

    void addSession(long userId, String sessionId);

    void deleteSession(long userId, String sessionId);

    User create(User user);

    UserDetailsService userDetailsService();
    UserDTO getUser(String email);

    void deleteUser(String email);
    void updateUser(String email, UserDTO userDTO);

    void createUser(String id, UserDTO request);

    User getCurrentUser();

    User getByUsername(String username);
}
