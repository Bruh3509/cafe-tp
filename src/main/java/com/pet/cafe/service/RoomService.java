package com.pet.cafe.service;

import com.pet.cafe.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getRooms();

    RoomDTO getRoom(int id);

    void deleteRoom(int id);

    RoomDTO updateRoom(int id, RoomDTO roomDTO);

    RoomDTO addRoom(int id, RoomDTO roomDTO);
}
