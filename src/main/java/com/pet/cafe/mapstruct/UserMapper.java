package com.pet.cafe.mapstruct;


import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(UserDTO userDTO, @Context String id);

    UserDTO entityToDto(User user);

    List<UserDTO> entitiesToDto(List<User> users);
}
