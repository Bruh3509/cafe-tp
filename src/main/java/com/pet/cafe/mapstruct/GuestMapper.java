package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.entity.Guest;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    //@Mapping(target = "phoneNumber", source = "id")
    Guest dtoToEntity(GuestDTO dto);

    GuestDTO entityToDto(Guest guest);

    List<GuestDTO> entitiesToDto(List<Guest> guests);
}
