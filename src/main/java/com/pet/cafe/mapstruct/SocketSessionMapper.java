package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.entity.SocketSession;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocketSessionMapper {
    SocketSession dtoToEntity(SocketSessionDTO dto);

    SocketSessionDTO entityToDto(SocketSession session);

    List<SocketSessionDTO> entitiesToDto(List<SocketSession> sessions);
}
