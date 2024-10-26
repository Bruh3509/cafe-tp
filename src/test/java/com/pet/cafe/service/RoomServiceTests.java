package com.pet.cafe.service;

import com.pet.cafe.dto.RoomDTO;
import com.pet.cafe.entity.Room;
import com.pet.cafe.mapstruct.RoomMapper;
import com.pet.cafe.repository.RoomRepository;
import com.pet.cafe.service.impl.RoomServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTests {
    @Mock
    private RoomRepository repository;
    @Mock
    private RoomMapper mapper;
    @InjectMocks
    private RoomServiceImpl service;

    @Test
    public void testGetWhenEntityExists(){
        int id = 1;
        Room room = mock(Room.class);
        RoomDTO roomDTO = mock(RoomDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(room));
        when(mapper.entityToDto(room)).thenReturn(roomDTO);

        var result = service.getRoom(id);
        assertSame(result, roomDTO);
    }

    @Test
    public void testGetWhenEntityNotExists(){
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getRoom(id));
    }

    @Test
    public void testGetAll(){
        List<Room> rooms = mock(List.class);
        List<RoomDTO> roomDTOS = mock(List.class);

        when(repository.findAll()).thenReturn(rooms);
        when(mapper.entitiesToDto(rooms)).thenReturn(roomDTOS);

        var result = service.getRooms();
        assertSame(result, roomDTOS);
    }

    @Test
    public void testCreate(){
        int roomNumber = 1;
        Room room = mock(Room.class);
        RoomDTO roomDTO = mock(RoomDTO.class);

        when(mapper.dtoToEntity(roomDTO, roomNumber)).thenReturn(room);
        when(repository.save(room)).thenReturn(room);
        when(mapper.entityToDto(room)).thenReturn(roomDTO);

        var result = service.addRoom(roomNumber, roomDTO);
        assertSame(result, roomDTO);
    }

    @Test
    public void testUpdate(){
        int id = 1;
        RoomDTO roomDto = getRoomDto();
        Room room = getRoom(id);

        when(repository.findById(id)).thenReturn(Optional.of(room));
        when(repository.save(room)).thenReturn(room);
        when(mapper.entityToDto(room)).thenReturn(roomDto);

        var result = service.updateRoom(id, roomDto);
        assertSame(result, roomDto);
    }

    @Test
    public void testUpdateWhenEntityNotExists(){
        int id = 1;
        RoomDTO roomDTO = mock(RoomDTO.class);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateRoom(id, roomDTO));
    }

    @Test
    public void testDelete(){
        int id = 1;
        Room room = getRoom(id);

        when(repository.findById(id)).thenReturn(Optional.of(room));
        doNothing().when(repository).delete(room);
        assertAll(() -> service.deleteRoom(id));
    }

    @Test
    public void testDeleteWhenEntityNotExists(){
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deleteRoom(id));
    }

    private Room getRoom(long id){
        return new Room(1,
                4,
                "roomType",
                new BigDecimal("1.0"),
                "description",
                "additFeatures");
    }

    private RoomDTO getRoomDto(){
        return new RoomDTO(4,
                "roomType",
                new BigDecimal("1.0"),
                "description",
                "additFeatures");
    }
}
