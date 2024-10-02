package com.pet.cafe.mapper;

import com.pet.cafe.dto.BookingDto;
import com.pet.cafe.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "userId", source = "user.passportId")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    BookingDto entityToDto(Booking booking);

    @Mapping(target = "userId", source = "user.passportId")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    List<BookingDto> entityToDto(List<Booking> booking);
}
