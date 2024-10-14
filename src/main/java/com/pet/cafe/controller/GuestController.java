package com.pet.cafe.controller;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.service.GuestService;
import com.pet.cafe.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService service;

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getGuests(){
        return new ResponseEntity<>(service.getGuests(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addGuest(@PathVariable String id, @RequestBody GuestDTO guestDTO){
        service.addGuest(id, guestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable String id){
        return new ResponseEntity<>(service.getGuest(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable String id){
        service.deleteGuest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGuest(@PathVariable String id, @RequestBody GuestDTO guestDTO){
        service.updateGuest(id, guestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
