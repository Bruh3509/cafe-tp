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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomServiceImpl implements RoomService {
    RoomRepository repository;
    RoomMapper mapper;

    @Override
    public List<RoomDTO> getRooms() {
        log.debug("Starting getRooms method.");
        var rooms = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved rooms.");
        log.debug("Leaving getRooms method.");
        return rooms;
    }

    @Override
    public RoomDTO getRoom(int id) {
        log.debug("Starting getRoom method with id {}.", id);
        var room = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Room with id {} not found!", id);
                    return new EntityNotFoundException("Room not found!");
                });

        log.info("Successfully retrieved room with id {}.", id);
        log.debug("Leaving getRoom method.");
        return mapper.entityToDto(room);
    }

    @Override
    public void deleteRoom(int id) {
        log.debug("Starting deleteRoom method with id {}.", id);
        var room = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete room with non-existent id {}.", id);
                    return new EntityNotFoundException("Room not found!");
                });

        repository.delete(room);
        log.info("Successfully removed room with id {}.", id);
        log.debug("Leaving deleteRoom method.");
    }

    @Override
    public RoomDTO updateRoom(int id, RoomDTO roomDTO) {
        log.debug("Starting updateRoom method with id {}.", id);
        var room = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to update room with non-existent id {}.", id);
                    return new EntityNotFoundException("Room not found.");
                });

        log.debug("Updating room fields with new values.");
        room.setCapacity(roomDTO.capacity());
        room.setRoomType(roomDTO.roomType());
        room.setCostPerNight(roomDTO.costPerNight());
        room.setDescription(roomDTO.description());
        room.setAdditionalFeatures(roomDTO.additionalFeatures());

        var updatedRoom = mapper.entityToDto(repository.save(room));
        log.info("Successfully updated room with id {}.", id);
        log.debug("Leaving updateRoom method.");
        return updatedRoom;
    }

    @Override
    public RoomDTO addRoom(int id, RoomDTO roomDTO) {
        log.debug("Starting addRoom method.");
        Room room = mapper.dtoToEntity(roomDTO, id);
        var createdRoom = mapper.entityToDto(repository.save(room));
        log.info("Successfully created room with id {}.", id);
        log.debug("Leaving addRoom method.");
        return createdRoom;
    }
}
