package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.BookingDTO;
import com.pet.cafe.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "room.roomNumber", source = "roomNumber")
    @Mapping(target = "user.passportId", source = "userId")
    Booking dtoToEntity(BookingDTO dto);

    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(target = "userId", source = "user.passportId")
    BookingDTO entityToDto(Booking booking);

    List<BookingDTO> entitiesToDto(List<Booking> bookings);
}
