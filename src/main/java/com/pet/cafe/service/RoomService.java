package com.pet.cafe.service;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.dto.UserDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getRooms();

    RoomDTO getRoom(int id);

    void deleteRoom(int id);

    void updateRoom(int id, RoomDTO roomDTO);

    void addRoom(int id, RoomDTO roomDTO);
}
