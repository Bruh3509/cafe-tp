package com.pet.cafe.service;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.mapstruct.UserMapper;
import com.pet.cafe.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.pet.cafe.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void testGetWhenEntityExists(){
        long id = 1;
        User user = mock(User.class);
        UserDTO userDTO = mock(UserDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.entityToDto(user)).thenReturn(userDTO);

        var result = service.getUser(id);
        assertSame(result, userDTO);
    }

    @Test
    void testGetWhenEntityNotExists(){
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getUser(id));
    }

    @Test
    void testGetAll(){
        List<User> users = mock(List.class);
        List<UserDTO> userDTOS = mock(List.class);

        when(repository.findAll()).thenReturn(users);
        when(userMapper.entitiesToDto(users)).thenReturn(userDTOS);

        var result = service.getUsers();
        assertSame(result, userDTOS);
    }

    @Test
    void testCreate(){
        User user = mock(User.class);
        UserDTO userDTO = mock(UserDTO.class);

        when(userMapper.dtoToEntity(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(userDTO);

        var result = service.createUser(userDTO);
        assertSame(result, userDTO);
    }

    @Test
    void testUpdate(){
        long id = 1;
        UserDTO userDTO = getUserDto();
        User user = getUser(id);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(userDTO);

        var result = service.updateUser(id, userDTO);
        assertSame(result, userDTO);
    }

    @Test
    void testUpdateWhenEntityNotExists(){
        long id = 1;
        UserDTO userDTO = mock(UserDTO.class);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateUser(id, userDTO));
    }

    @Test
    void testDelete(){
        long id = 1;
        User user = getUser(id);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(repository).delete(user);
        assertAll(() -> service.deleteUser(id));
    }

    @Test
    void testDeleteWhenEntityNotExists(){
        long id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deleteUser(id));
    }

    private User getUser(long id){
        return new User(id,
                "passportId",
                "email",
                "firstname",
                "secondname",
                "lastname",
                "phonenum");
    }

    private UserDTO getUserDto(){
        return new UserDTO("passportId",
                "email",
                "firstname",
                "secondname",
                "lastname",
                "phonenum");
    }
}
