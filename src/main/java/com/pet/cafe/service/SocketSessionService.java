package com.pet.cafe.service;

import com.pet.cafe.dto.SocketSessionDTO;

import java.util.List;

public interface SocketSessionService {
    List<SocketSessionDTO> getSessions();

    SocketSessionDTO getSession(String  id);

    void deleteSession(String id);

//    SocketSessionDTO updateSession(String id, SocketSessionDTO sessionDTO);

    SocketSessionDTO addSession(SocketSessionDTO sessionDTO);
}
