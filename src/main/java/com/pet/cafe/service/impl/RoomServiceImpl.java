package com.pet.cafe.service.impl;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.entity.Room;
import com.pet.cafe.mapstruct.RoomMapper;
import com.pet.cafe.repository.RoomRepository;
import com.pet.cafe.service.RoomService;
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
public class RoomServiceImpl implements RoomService {
    RoomRepository repository;
    RoomMapper mapper;

    @Override
    public List<RoomDTO> getRooms() {
        return mapper.entitiesToDto(repository.findAll());
    }

    @Override
    public RoomDTO getRoom(int id) {
        Optional<Room> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Room not found!");
        }

        return mapper.entityToDto(user.get());
    }

    @Override
    public void deleteRoom(int id) {
        Optional<Room> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Room not found!");
        }

        repository.delete(user.get());
    }

    @Override
    public void updateRoom(int id, RoomDTO roomDTO) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Room not found.");
        }

        Room room = mapper.dtoToEntity(roomDTO, id);
        repository.save(room);
    }

    @Override
    public void addRoom(int id, RoomDTO roomDTO) {
        Room room = mapper.dtoToEntity(roomDTO, id);
        repository.save(room);
    }
}
