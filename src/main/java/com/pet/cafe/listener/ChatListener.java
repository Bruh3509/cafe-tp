package com.pet.cafe.listener;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.service.impl.SocketSessionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class ChatListener {
    SocketSessionServiceImpl sessionService;

    @EventListener
    public void handleConnectEvent(SessionConnectedEvent e) {
        log.info("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(e.getMessage());

        String sessionId = headerAccessor.getSessionId();
        long userId = (long) headerAccessor
                .getSessionAttributes()
                .get("userId");

        sessionService.addSession(new SocketSessionDTO(sessionId, userId));
    }


    @EventListener
    public void handleDisconnectEvent(SessionDisconnectEvent e) {
        log.info("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(e.getMessage());

        String sessionId = headerAccessor.getSessionId();
        sessionService.deleteSession(sessionId);
    }
}
