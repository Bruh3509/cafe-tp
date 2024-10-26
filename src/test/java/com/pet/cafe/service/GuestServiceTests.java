package com.pet.cafe.service;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.entity.Guest;
import com.pet.cafe.mapstruct.GuestMapper;
import com.pet.cafe.repository.GuestRepository;
import com.pet.cafe.service.impl.GuestServiceImpl;
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
public class GuestServiceTests {
    @Mock
    private GuestRepository repository;
    @Mock
    private GuestMapper mapper;
    @InjectMocks
    private GuestServiceImpl service;

    @Test
    public void testGetWhenEntityExists(){
        long id = 1;
        Guest guest = mock(Guest.class);
        GuestDTO guestDTO = mock(GuestDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(guest));
        when(mapper.entityToDto(guest)).thenReturn(guestDTO);

        var result = service.getGuest(id);
        assertSame(result, guestDTO);
    }

    @Test
    public void testGetWhenEntityNotExists(){
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getGuest(id));
    }

    @Test
    public void testGetAll(){
        List<Guest> guests = mock(List.class);
        List<GuestDTO> guestDTOS = mock(List.class);

        when(repository.findAll()).thenReturn(guests);
        when(mapper.entitiesToDto(guests)).thenReturn(guestDTOS);

        var result = service.getGuests();
        assertSame(result, guestDTOS);
    }

    @Test
    public void testCreate(){
        Guest guest = mock(Guest.class);
        GuestDTO guestDTO = mock(GuestDTO.class);

        when(mapper.dtoToEntity(guestDTO)).thenReturn(guest);
        when(repository.save(guest)).thenReturn(guest);
        when(mapper.entityToDto(guest)).thenReturn(guestDTO);

        var result = service.addGuest(guestDTO);
        assertSame(result, guestDTO);
    }

    @Test
    public void testUpdate(){
        long id = 1;
        GuestDTO guestDTO = getUserDto();
        Guest guest = getUser(id);

        when(repository.findById(id)).thenReturn(Optional.of(guest));
        when(repository.save(guest)).thenReturn(guest);
        when(mapper.entityToDto(guest)).thenReturn(guestDTO);

        var result = service.updateGuest(id, guestDTO);
        assertSame(result, guestDTO);
    }

    @Test
    public void testUpdateWhenEntityNotExists(){
        long id = 1;
        GuestDTO userDTO = mock(GuestDTO.class);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateGuest(id, userDTO));
    }

    @Test
    public void testDelete(){
        long id = 1;
        Guest guest = getUser(id);

        when(repository.findById(id)).thenReturn(Optional.of(guest));
        doNothing().when(repository).delete(guest);
        assertAll(() -> service.deleteGuest(id));
    }

    @Test
    public void testDeleteWhenEntityNotExists(){
        long id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deleteGuest(id));
    }

    private Guest getUser(long id){
        return new Guest(id,
                "phoneNumber",
                "firstname",
                "secondname",
                "lastname");
    }

    private GuestDTO getUserDto(){
        return new GuestDTO("phoneNumber",
                "firstname",
                "secondname",
                "lastname");
    }
}
