package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.entity.Guest;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    Guest dtoToEntity(GuestDTO dto, @Context String id);

    GuestDTO entityToDto(Guest guest);

    List<GuestDTO> entitiesToDto(List<Guest> guests);
}
