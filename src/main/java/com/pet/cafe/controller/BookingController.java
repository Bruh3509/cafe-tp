package com.pet.cafe.controller;

import com.pet.cafe.dto.BookingDTO;
import com.pet.cafe.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookings(){
        log.debug("Received request to get all bookings.");
        var bookings = service.getBookings();
        log.info("Successfully retrieved bookings.");
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO){
        log.debug("Received request to create a booking.");
        var booking = service.addBooking(bookingDTO);
        log.info("Successfully created booking.");
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable long id){
        log.debug("Received request to get the booking with id {}.", id);
        var booking = service.getBooking(id);
        log.info("Successfully retrieved booking with id {}.", id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id){
        log.debug("Received request to delete the booking with id {}.", id);
        service.deleteBooking(id);
        log.info("Successfully deleted booking with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable long id, @RequestBody BookingDTO bookingDTO){
        log.debug("Received request to put the booking with id {}.", id);
        var booking = service.updateBooking(id, bookingDTO);
        log.info("Successfully put booking with id {}.", id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
