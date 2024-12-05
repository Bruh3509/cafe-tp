package com.pet.cafe.service;

import com.pet.cafe.dto.BookingDTO;
import com.pet.cafe.entity.Booking;
import com.pet.cafe.entity.Room;
import com.pet.cafe.entity.User;
import com.pet.cafe.mapstruct.BookingMapper;
import com.pet.cafe.repository.BookingRepository;
import com.pet.cafe.service.impl.BookingServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTests {
    @Mock
    private BookingRepository repository;
    @Mock
    private BookingMapper userMapper;
    @InjectMocks
    private BookingServiceImpl service;

    @Test
    void testGetWhenEntityExists(){
        long id = 1;
        Booking booking = mock(Booking.class);
        BookingDTO bookingDTO = mock(BookingDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(booking));
        when(userMapper.entityToDto(booking)).thenReturn(bookingDTO);

        var result = service.getBooking(id);
        assertSame(result, bookingDTO);
    }

    @Test
    void testGetWhenEntityNotExists(){
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getBooking(id));
    }

    @Test
    void testGetAll(){
        List<Booking> bookings = mock(List.class);
        List<BookingDTO> bookingDTOS = mock(List.class);

        when(repository.findAll()).thenReturn(bookings);
        when(userMapper.entitiesToDto(bookings)).thenReturn(bookingDTOS);

        var result = service.getBookings();
        assertSame(result, bookingDTOS);
    }

    @Test
    void testCreate(){
        Booking booking = mock(Booking.class);
        BookingDTO bookingDTO = mock(BookingDTO.class);

        when(userMapper.dtoToEntity(bookingDTO)).thenReturn(booking);
        when(repository.save(booking)).thenReturn(booking);
        when(userMapper.entityToDto(booking)).thenReturn(bookingDTO);

        var result = service.addBooking(bookingDTO);
        assertSame(result, bookingDTO);
    }

    @Test
    void testUpdate(){
        long id = 1;
        BookingDTO bookingDTO = getBookingDto();
        Booking booking = getBooking(id);

        when(repository.findById(id)).thenReturn(Optional.of(booking));
        when(repository.save(booking)).thenReturn(booking);
        when(userMapper.entityToDto(booking)).thenReturn(bookingDTO);

        var result = service.updateBooking(id, bookingDTO);
        assertSame(result, bookingDTO);
    }

    @Test
    void testUpdateWhenEntityNotExists(){
        long id = 1;
        BookingDTO bookingDTO = mock(BookingDTO.class);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateBooking(id, bookingDTO));
    }

    @Test
    void testDelete(){
        long id = 1;
        Booking booking = getBooking(id);

        when(repository.findById(id)).thenReturn(Optional.of(booking));
        doNothing().when(repository).delete(booking);
        assertAll(() -> service.deleteBooking(id));
    }

    @Test
    void testDeleteWhenEntityNotExists(){
        long id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deleteBooking(id));
    }

    private Booking getBooking(long id){
        User user = new User(id,
                "passportId",
                "email",
                "firstname",
                "secondname",
                "lastname",
                "phonenum");

        Room room = new Room(1,
                4,
                "roomType",
                new BigDecimal("1.0"),
                "description",
                "additFeatures");

        return new Booking(id, new Date(), new Date(), user, room);
    }

    private BookingDTO getBookingDto(){
        return new BookingDTO(new Date(),
                new Date(),
                "userId",
                1);
    }
}
