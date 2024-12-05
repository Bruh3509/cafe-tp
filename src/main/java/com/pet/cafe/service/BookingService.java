package com.pet.cafe.service;

import com.pet.cafe.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getBookings();

    BookingDTO getBooking(long id);

    void deleteBooking(long id);

    void updateBooking(long id, BookingDTO bookingDTO);

    void addBooking(BookingDTO bookingDTO);
}
