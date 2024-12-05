package com.pet.cafe.listener;

import com.pet.cafe.MessageSender;
import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.service.MessageService;
import com.pet.cafe.service.SocketSessionService;
import com.pet.cafe.service.UserService;
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
    SocketSessionService sessionService;
    MessageService messageService;
    UserService userService;
    MessageSender sender;

    @EventListener
    public void handleConnectEvent(SessionConnectedEvent e) {
        log.info("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(e.getMessage());

        String sessionId = headerAccessor.getSessionId();
        long userId = Long.parseLong(headerAccessor.getFirstNativeHeader("userId"));

        var messages = messageService.getMessages();
        for(MessageDTO m: messages){
            if(m.getRecipientId() == userId){
                sender.sendMessageToUser(m);
                messageService.deleteMessage(m.getMessageId());
                break;
            }
        }

        try {
            sessionService.addSession(new SocketSessionDTO(sessionId, userId));
            userService.addSession(userId, sessionId);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    @EventListener
    public void handleDisconnectEvent(SessionDisconnectEvent e) {
        log.info("Received a new web socket disconnection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(e.getMessage());

        long userId = Long.parseLong(headerAccessor.getFirstNativeHeader("userId"));
        String sessionId = headerAccessor.getSessionId();

        userService.deleteSession(userId, sessionId);
        sessionService.deleteSession(sessionId);
    }
}
