package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.entity.Room;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "roomNumber", source = "id")
    Room dtoToEntity(RoomDTO dto, int id);

    RoomDTO entityToDto(Room room);

    List<RoomDTO> entitiesToDto(List<Room> rooms);
}
