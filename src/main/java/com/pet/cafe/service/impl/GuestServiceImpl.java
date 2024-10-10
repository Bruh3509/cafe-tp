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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GuestServiceImpl implements GuestService {
    GuestRepository repository;
    GuestMapper mapper;

    @Override
    public List<GuestDTO> getGuests() {
        return mapper.entitiesToDto(repository.findAll());
    }

    @Override
    public GuestDTO getGuest(String id) {
        var guest = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found!"));

        return mapper.entityToDto(guest);
    }

    @Override
    public void deleteGuest(String id) {
        var guest = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found!"));

        repository.delete(guest);
    }

    @Override
    public void updateGuest(String id, GuestDTO guestDTO) {
        var guest = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found."));

        guest.setFirstName(guestDTO.firstName());
        guest.setSecondName(guestDTO.secondName());
        guest.setLastName(guestDTO.lastName());
        repository.save(guest);
    }

    @Override
    public void addGuest(String id, GuestDTO guestDTO) {
        Guest guest = mapper.dtoToEntity(guestDTO, id);
        repository.save(guest);
    }
}
