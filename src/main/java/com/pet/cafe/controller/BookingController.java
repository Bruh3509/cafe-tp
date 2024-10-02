package com.pet.cafe.controller;

import com.pet.cafe.dto.BookingDto;
import com.pet.cafe.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookingDto>> getAllBooks() {
        return new ResponseEntity<>(bookingService.getAllBooks(), HttpStatus.OK);
    }
}
