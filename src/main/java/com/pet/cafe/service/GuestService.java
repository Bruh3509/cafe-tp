package com.pet.cafe.service;

import com.pet.cafe.dto.GuestDTO;

import java.util.List;

public interface GuestService {
    List<GuestDTO> getGuests();

    GuestDTO getGuest(long id);

    void deleteGuest(long id);

    GuestDTO updateGuest(long id, GuestDTO guestDTO);

    GuestDTO addGuest(GuestDTO guestDTO);
}
