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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService {
    BookingRepository repository;
    BookingMapper mapper;

    @Override
    public List<BookingDTO> getBookings() {
        return mapper.entitiesToDto(repository.findAll());
    }

    @Override
    public BookingDTO getBooking(long id) {
        var booking = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found!"));

        return mapper.entityToDto(booking);
    }

    @Override
    public void deleteBooking(long id) {
        var booking = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found!"));

        repository.delete(booking);
    }

    @Override
    public BookingDTO updateBooking(long id, BookingDTO bookingDTO) {
        var booking = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found."));

        booking.setDateTo(bookingDTO.dateTo());
        booking.setDateFrom(bookingDTO.dateFrom());

        return mapper.entityToDto(repository.save(booking));
    }

    @Override
    public BookingDTO addBooking(BookingDTO bookingDTO) {
        Booking booking = mapper.dtoToEntity(bookingDTO);
        return mapper.entityToDto(repository.save(booking));
    }
}
