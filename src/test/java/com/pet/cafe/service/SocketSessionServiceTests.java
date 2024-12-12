package com.pet.cafe.service;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.entity.SocketSession;
import com.pet.cafe.entity.User;
import com.pet.cafe.mapstruct.SocketSessionMapper;
import com.pet.cafe.repository.SocketSessionRepository;
import com.pet.cafe.service.impl.SocketSessionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SocketSessionServiceTests {
    @Mock
    private SocketSessionRepository repository;
    @Mock
    private SocketSessionMapper mapper;
    @InjectMocks
    private SocketSessionServiceImpl service;

    @Test
    void testGetWhenEntityExists(){
        String id = "1";
        SocketSession session = mock(SocketSession.class);
        SocketSessionDTO sessionDTO = mock(SocketSessionDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(session));
        when(mapper.entityToDto(session)).thenReturn(sessionDTO);

        var result = service.getSession(id);
        assertSame(result, sessionDTO);
    }

    @Test
    void testGetWhenEntityNotExists(){
        String id = "1";
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getSession(id));
    }

    @Test
    void testGetAll(){
        List<SocketSession> sessions = mock(List.class);
        List<SocketSessionDTO> sessionDTOS = mock(List.class);

        when(repository.findAll()).thenReturn(sessions);
        when(mapper.entitiesToDto(sessions)).thenReturn(sessionDTOS);

        var result = service.getSessions();
        assertSame(result, sessionDTOS);
    }

    @Test
    void testCreate(){
        SocketSession session = mock(SocketSession.class);
        SocketSessionDTO sessionDTO = mock(SocketSessionDTO.class);

        when(mapper.dtoToEntity(sessionDTO)).thenReturn(session);
        when(repository.save(session)).thenReturn(session);
        when(mapper.entityToDto(session)).thenReturn(sessionDTO);

        var result = service.addSession(sessionDTO);
        assertSame(result, sessionDTO);
    }

//    @Test
//    void testUpdate(){
//        String id = "1";
//        SocketSessionDTO sessionDTO = getSocketSessionDto();
//        SocketSession session = getSocketSession(id);
//
//        when(repository.findById(id)).thenReturn(Optional.of(session));
//        when(repository.save(session)).thenReturn(session);
//        when(mapper.entityToDto(session)).thenReturn(sessionDTO);
//
//        var result = service.updateRoom(id, sessionDTO);
//        assertSame(result, sessionDTO);
//    }

//    @Test
//    void testUpdateWhenEntityNotExists(){
//        String id = "1";
//        SocketSessionDTO sessionDTO = mock(SocketSessionDTO.class);
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, () -> service.updateRoom(id, sessionDTO));
//    }

    @Test
    void testDelete(){
        String id = "1";
        SocketSession session = getSocketSession(id);

        when(repository.findById(id)).thenReturn(Optional.of(session));
        doNothing().when(repository).delete(session);
        assertAll(() -> service.deleteSession(id));
    }

    @Test
    void testDeleteWhenEntityNotExists(){
        String id = "1";

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deleteSession(id));
    }

    private SocketSession getSocketSession(String id){
        User user = new User("1",
                "passportId",
                "email",
                "firstname",
                "secondname",
                "lastname",
                "phonenum");

        return new SocketSession(id, user);
    }

    private SocketSessionDTO getSocketSessionDto(){
        return new SocketSessionDTO("sessionId", 1);
    }
}
