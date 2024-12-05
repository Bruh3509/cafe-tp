package com.pet.cafe.mapstruct;

import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "sender.passportId", source = "senderId")
    @Mapping(target = "recipient.passportId", source = "recipientId")
    Message dtoToEntity(MessageDTO dto);

    @Mapping(target = "senderId", source = "sender.passportId")
    @Mapping(target = "recipientId", source = "recipient.passportId")
    MessageDTO entityToDto(Message message);

    List<MessageDTO> entitiesToDto(List<Message> messages);
}
