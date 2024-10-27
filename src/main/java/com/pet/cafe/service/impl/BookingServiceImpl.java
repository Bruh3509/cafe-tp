package com.pet.cafe.service.impl;

import com.pet.cafe.dto.BookingDTO;
import com.pet.cafe.entity.Booking;
import com.pet.cafe.mapstruct.BookingMapper;
import com.pet.cafe.repository.BookingRepository;
import com.pet.cafe.service.BookingService;
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
public class BookingServiceImpl implements BookingService {
    BookingRepository repository;
    BookingMapper mapper;

    @Override
    public List<BookingDTO> getBookings() {
        log.debug("Starting getBookings method.");
        var bookings = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved bookings.");
        log.debug("Leaving getBookings method.");
        return bookings;
    }

    @Override
    public BookingDTO getBooking(long id) {
        log.debug("Starting getBooking method with id {}.", id);
        var booking = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Booking with id {} not found!", id);
                    return new EntityNotFoundException("Booking not found!");
                });

        log.info("Successfully retrieved booking with id {}.", id);
        log.debug("Leaving getBooking method.");
        return mapper.entityToDto(booking);
    }

    @Override
    public void deleteBooking(long id) {
        log.debug("Starting deleteBooking method with id {}.", id);
        var booking = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete booking with non-existent id {}.", id);
                    return new EntityNotFoundException("Booking not found!");
                });

        repository.delete(booking);
        log.info("Successfully removed booking with id {}.", id);
        log.debug("Leaving deleteBooking method.");
    }

    @Override
    public BookingDTO updateBooking(long id, BookingDTO bookingDTO) {
        log.debug("Starting updateBooking method with id {}.", id);
        var booking = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to update booking with non-existent id {}.", id);
                    return new EntityNotFoundException("Booking not found.");
                });

        log.debug("Updating booking fields with new values.");
        booking.setDateTo(bookingDTO.dateTo());
        booking.setDateFrom(bookingDTO.dateFrom());

        var updatedBooking = mapper.entityToDto(repository.save(booking));
        log.info("Successfully updated booking with id {}.", id);
        log.debug("Leaving updateBooking method.");
        return updatedBooking;
    }

    @Override
    public BookingDTO addBooking(BookingDTO bookingDTO) {
        log.debug("Starting addBooking method.");
        Booking booking = mapper.dtoToEntity(bookingDTO);
        var createdBooking = mapper.entityToDto(repository.save(booking));
        log.info("Successfully created booking.");
        log.debug("Leaving addBooking method.");
        return createdBooking;
    }
}
