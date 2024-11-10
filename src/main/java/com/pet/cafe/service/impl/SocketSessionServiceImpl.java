package com.pet.cafe.service.impl;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.entity.SocketSession;
import com.pet.cafe.mapstruct.SocketSessionMapper;
import com.pet.cafe.repository.SocketSessionRepository;
import com.pet.cafe.service.SocketSessionService;
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
public class SocketSessionServiceImpl implements SocketSessionService {
    SocketSessionRepository repository;
    SocketSessionMapper mapper;

    @Override
    public List<SocketSessionDTO> getSessions() {
        log.debug("Starting getSessions method.");
        var sessions = mapper.entitiesToDto(repository.findAll());
        log.info("Successfully retrieved sessions.");
        log.debug("Leaving getSessions method.");
        return sessions;
    }

    @Override
    public SocketSessionDTO getSession(String id) {
        log.debug("Starting getSession method with id {}.", id);
        var session = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Session with id {} not found!", id);
                    return new EntityNotFoundException("Session not found!");
                });

        log.info("Successfully retrieved session with id {}.", id);
        log.debug("Leaving getSession method.");
        return mapper.entityToDto(session);
    }

    @Override
    public void deleteSession(String id) {
        log.debug("Starting deleteSession method with id {}.", id);
        var session = repository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Attempt to delete session with non-existent id {}.", id);
                    return new EntityNotFoundException("Session not found!");
                });

        repository.delete(session);
        log.info("Successfully removed session with id {}.", id);
        log.debug("Leaving deleteSession method.");
    }

//    @Override
//    public SocketSessionDTO updateSession(String id, SocketSessionDTO sessionDTO) {
//        log.debug("Starting updateSession method with id {}.", id);
//        var session = repository
//                .findById(id)
//                .orElseThrow(() -> {
//                    log.error("Attempt to update session with non-existent id {}.", id);
//                    return new EntityNotFoundException("Room not found.");
//                });
//
//        log.debug("Updating session fields with new values.");
//        session.setSessionId(sessionDTO.sessionId());
//        session.setUserId(sessionDTO.userId());
//
//        var updatedSession = mapper.entityToDto(repository.save(session));
//        log.info("Successfully updated session with id {}.", id);
//        log.debug("Leaving updateSession method.");
//        return updatedSession;
//    }

    @Override
    public SocketSessionDTO addSession(SocketSessionDTO sessionDTO) {
        log.debug("Starting addSession method.");
        SocketSession session = mapper.dtoToEntity(sessionDTO);
        var createdSession = mapper.entityToDto(repository.save(session));
        log.info("Successfully created session with id {}.", sessionDTO.sessionId());
        log.debug("Leaving addSession method.");
        return createdSession;
    }
}
