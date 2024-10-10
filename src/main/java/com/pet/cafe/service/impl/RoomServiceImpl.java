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
        var room = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found!"));

        return mapper.entityToDto(room);
    }

    @Override
    public void deleteRoom(int id) {
        var room = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found!"));

        repository.delete(room);
    }

    @Override
    public void updateRoom(int id, RoomDTO roomDTO) {
        var room = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found."));

        room.setCapacity(roomDTO.capacity());
        room.setRoomType(roomDTO.roomType());
        room.setCostPerNight(roomDTO.costPerNight());
        room.setDescription(roomDTO.description());
        room.setAdditionalFeatures(roomDTO.additionalFeatures());

        repository.save(room);
    }

    @Override
    public void addRoom(int id, RoomDTO roomDTO) {
        Room room = mapper.dtoToEntity(roomDTO, id);
        repository.save(room);
    }
}
