package com.pet.cafe.controller;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms(){
        return new ResponseEntity<>(service.getRooms(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addRoom(@PathVariable int id, @RequestBody RoomDTO request){
        service.addRoom(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable int id){
        return new ResponseEntity<>(service.getRoom(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id){
        service.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable int id, @RequestBody RoomDTO roomDTO){
        service.updateRoom(id, roomDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
