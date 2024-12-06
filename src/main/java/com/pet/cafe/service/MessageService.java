package com.pet.cafe.service;

import com.pet.cafe.dto.GuestDTO;
import com.pet.cafe.dto.message.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getMessages();

    MessageDTO getMessage(long id);

    void deleteMessage(long id);

    MessageDTO updateMessage(long id, MessageDTO message);

    MessageDTO addMessage(MessageDTO message);
}
