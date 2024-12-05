package com.pet.cafe.service.impl;

import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.entity.Message;
import com.pet.cafe.mapstruct.MessageMapper;
import com.pet.cafe.repository.MessageRepository;
import com.pet.cafe.repository.UserRepository;
import com.pet.cafe.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageServiceImpl implements MessageService {
    MessageRepository repository;
    UserRepository userRepository;
    MessageMapper mapper;

    @Override
    public List<MessageDTO> getMessages() {
        log.debug("Starting getMessages method.");
        var messages = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved messages.");
        log.debug("Leaving getMessages method.");
        return messages;
    }

    @Override
    public MessageDTO getMessage(long id) {
        log.debug("Starting getMessage method with id {}.", id);
        var message = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", id);
                    return new EntityNotFoundException("Guest not found!");
                });

        log.info("Successfully retrieved message with id {}.", id);
        log.debug("Leaving getMessage method.");
        return mapper.entityToDto(message);
    }

    @Override
    public void deleteMessage(long id) {
        log.debug("Starting deleteMessage method with id {}.", id);
        var message = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete message with non-existent id {}.", id);
                    return new EntityNotFoundException("Guest not found!");
                });

        repository.delete(message);
        log.info("Successfully removed message with id {}.", id);
        log.debug("Leaving deleteMessage method.");
    }

    @Override
    public MessageDTO updateMessage(long id, MessageDTO messageDTO) {
        log.debug("Starting updateMessage method with id {}.", id);
        var message = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to update message with non-existent id {}.", id);
                    return new EntityNotFoundException("Guest not found.");
                });

        log.debug("Updating message fields with new values.");
        userRepository
                .findById(messageDTO.getRecipientId())
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", messageDTO.getRecipientId());
                    return new EntityNotFoundException("Guest not found!");
                });
        userRepository
                .findById(messageDTO.getSenderId())
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", messageDTO.getSenderId());
                    return new EntityNotFoundException("Guest not found!");
                });
        message.setText(messageDTO.getText());
        message.setFileName(messageDTO.getFileName());
        message.setFileType(messageDTO.getFileType());
        message.setFileData(messageDTO.getFileData());

        var updatedMessage = mapper.entityToDto(repository.save(message));
        log.info("Successfully updated message with id {}.", id);
        log.debug("Leaving updateMessage method.");
        return updatedMessage;
    }

    @Override
    public MessageDTO addMessage(MessageDTO messageDTO) {
        log.debug("Starting addMessage method.");
        Message message = mapper.dtoToEntity(messageDTO);
        userRepository
                .findById(messageDTO.getRecipientId())
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", messageDTO.getRecipientId());
                    return new EntityNotFoundException("Guest not found!");
                });
        userRepository
                .findById(messageDTO.getSenderId())
                .orElseThrow(() -> {
                    log.error("Guest with id {} not found!", messageDTO.getSenderId());
                    return new EntityNotFoundException("Guest not found!");
                });

        var createdMessage = mapper.entityToDto(repository.save(message));
        log.info("Successfully created message.");
        log.debug("Leaving addMessage method.");
        return createdMessage;
    }
}
