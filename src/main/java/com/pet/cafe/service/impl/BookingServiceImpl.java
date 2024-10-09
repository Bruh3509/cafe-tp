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
import java.util.Optional;

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
        Optional<Booking> booking = repository.findById(id);
        if (booking.isEmpty()) {
            throw new EntityNotFoundException("Booking not found!");
        }

        return mapper.entityToDto(booking.get());
    }

    @Override
    public void deleteBooking(long id) {
        Optional<Booking> booking = repository.findById(id);
        if (booking.isEmpty()) {
            throw new EntityNotFoundException("Booking not found!");
        }

        repository.delete(booking.get());
    }

    @Override
    public void updateBooking(long id, BookingDTO bookingDTO) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Booking not found.");
        }

        Booking booking = mapper.dtoToEntity(bookingDTO);
        repository.save(booking);
    }

    @Override
    public void addBooking(BookingDTO bookingDTO) {
        Booking booking = mapper.dtoToEntity(bookingDTO);
        repository.save(booking);
    }
}
