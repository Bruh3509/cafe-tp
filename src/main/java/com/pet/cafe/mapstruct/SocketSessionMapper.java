package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.entity.SocketSession;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SocketSessionMapper {
    SocketSession dtoToEntity(SocketSessionDTO dto);

    SocketSessionDTO entityToDto(SocketSession session);

    List<SocketSessionDTO> entitiesToDto(List<SocketSession> sessions);
    Set<SocketSessionDTO> entitiesToDto(Set<SocketSession> sessions);
}
