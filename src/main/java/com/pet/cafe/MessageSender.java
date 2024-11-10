package com.pet.cafe;

import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.message.Message;
import com.pet.cafe.service.SocketSessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageSender {
    SimpMessagingTemplate messagingTemplate;
    SocketSessionService sessionService;

    public void sendMessageToOnlineUsers(Message message){
        List<SocketSessionDTO> sessions = sessionService.getSessions();
        sessions.forEach(s -> messagingTemplate.convertAndSendToUser(
                String.valueOf(s.userId()),
                "/hotel/public",
                message.text));
    }
}
