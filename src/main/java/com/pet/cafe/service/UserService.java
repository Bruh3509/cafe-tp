package com.pet.cafe.service;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.dto.message.MessageDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUser(long id);

    void deleteUser(long id);

    UserDTO updateUser(long id, UserDTO userDTO);

    UserDTO createUser(UserDTO userDTO);

    Set<SocketSessionDTO> getSessions(long id);

    void addSession(long userId, String sessionId);

    void deleteSession(long userId, String sessionId);
}
