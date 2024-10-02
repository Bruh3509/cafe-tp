package com.pet.cafe.service.impl;

import com.pet.cafe.repository.BookingRepository;
import com.pet.cafe.dto.BookingDto;
import com.pet.cafe.mapper.BookingMapper;
import com.pet.cafe.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    @Override
    public List<BookingDto> getAllBooks() {
        var books = bookingRepository.findAll();
        return bookingMapper.entityToDto(books);
    }
}
