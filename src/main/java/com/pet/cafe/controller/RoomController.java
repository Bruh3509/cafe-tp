package com.pet.cafe.controller;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms() {
        log.debug("Received request to get all rooms.");
        var rooms = service.getRooms();
        log.info("Successfully retrieved rooms.");
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addRoom(@PathVariable int id, @RequestBody RoomDTO request) {
        log.debug("Received request to create a room with id {}.", id);
        service.addRoom(id, request);
        log.info("Successfully created room with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable int id) {
        log.debug("Received request to get the room with id {}.", id);
        var room = service.getRoom(id);
        log.info("Successfully retrieved room with id {}.", id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        log.debug("Received request to delete the room with id {}.", id);
        service.deleteRoom(id);
        log.info("Successfully deleted room with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable int id, @RequestBody RoomDTO roomDTO) {
        log.debug("Received request to update the room with id {}.", id);
        service.updateRoom(id, roomDTO);
        log.info("Successfully updated room with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
