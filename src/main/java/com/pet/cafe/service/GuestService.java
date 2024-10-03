package com.pet.cafe.service;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.dto.RoomDTO;

import java.util.List;

public interface GuestService {
    List<GuestDTO> getGuests();
    GuestDTO getGuest(String id);
    void deleteGuest(String  id);
    void updateGuest(String id, GuestDTO guestDTO);
    void addGuest(String id, GuestDTO guestDTO);
}
