package com.pet.cafe.controller;

import com.pet.cafe.dto.BookingDTO;
import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.service.BookingService;
import com.pet.cafe.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookings(){
        return new ResponseEntity<>(service.getBookings(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> addBooking(@RequestBody BookingDTO bookingDTO){
        service.addBooking(bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable long id){
        return new ResponseEntity<>(service.getBooking(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id){
        service.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBooking(@PathVariable long id, @RequestBody BookingDTO bookingDTO){
        service.updateBooking(id, bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
