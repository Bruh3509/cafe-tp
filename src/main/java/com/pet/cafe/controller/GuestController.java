package com.pet.cafe.controller;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService service;

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getGuests() {
        log.debug("Received request to get all guests.");
        var guests = service.getGuests();
        log.info("Successfully retrieved guests.");
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GuestDTO> addGuest(@RequestBody GuestDTO guestDTO) {
        log.debug("Received request to create a guest.");
        var guest = service.addGuest(guestDTO);
        log.info("Successfully created guest.");
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable long id) {
        log.debug("Received request to get the guest with id {}.", id);
        var guest = service.getGuest(id);
        log.info("Successfully retrieved guest with id {}.", id);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable long id) {
        log.debug("Received request to delete the guest with id {}.", id);
        service.deleteGuest(id);
        log.info("Successfully deleted guest with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable long id, @RequestBody GuestDTO guestDTO) {
        log.debug("Received request to update the guest with id {}.", id);
        var guest = service.updateGuest(id, guestDTO);
        log.info("Successfully updated guest with id {}.", id);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }
}
