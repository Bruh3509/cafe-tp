package com.pet.cafe.service.impl;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.entity.Guest;
import com.pet.cafe.mapstruct.GuestMapper;
import com.pet.cafe.repository.GuestRepository;
import com.pet.cafe.service.GuestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GuestServiceImpl implements GuestService {
    GuestRepository repository;
    GuestMapper mapper;

    @Override
    public List<GuestDTO> getGuests() {
        log.debug("Starting getGuests method.");
        var guests = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved guests.");
        log.debug("Leaving getGuests method.");
        return guests;
    }

    @Override
    public GuestDTO getGuest(long id) {
        log.debug("Starting getGuest method with id {}.", id);
        var guest = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", id);
                    return new EntityNotFoundException("Guest not found!");
                });

        log.info("Successfully retrieved guest with id {}.", id);
        log.debug("Leaving getGuest method.");
        return mapper.entityToDto(guest);
    }

    @Override
    public void deleteGuest(long id) {
        log.debug("Starting deleteGuest method with id {}.", id);
        var guest = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete guest with non-existent id {}.", id);
                    return new EntityNotFoundException("Guest not found!");
                });

        repository.delete(guest);
        log.info("Successfully removed guest with id {}.", id);
        log.debug("Leaving deleteGuest method.");
    }

    @Override
    public GuestDTO updateGuest(long id, GuestDTO guestDTO) {
        log.debug("Starting updateGuest method with id {}.", id);
        var guest = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to update guest with non-existent id {}.", id);
                    return new EntityNotFoundException("Guest not found.");
                });

        log.debug("Updating guest fields with new values.");
        guest.setPhoneNumber(guestDTO.phoneNumber());
        guest.setFirstName(guestDTO.firstName());
        guest.setSecondName(guestDTO.secondName());
        guest.setLastName(guestDTO.lastName());

        var updatedGuest = mapper.entityToDto(repository.save(guest));
        log.info("Successfully updated guest with id {}.", id);
        log.debug("Leaving updateGuest method.");
        return updatedGuest;
    }

    @Override
    public GuestDTO addGuest(GuestDTO guestDTO) {
        log.debug("Starting addGuest method.");
        Guest guest = mapper.dtoToEntity(guestDTO);
        var createdGuest = mapper.entityToDto(repository.save(guest));
        log.info("Successfully created guest.");
        log.debug("Leaving addGuest method.");
        return createdGuest;
    }
}
