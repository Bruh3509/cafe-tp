package com.pet.cafe;

import com.pet.cafe.dto.message.MessageDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageSender {
    SimpMessagingTemplate messagingTemplate;

    public void sendMessageToUser(MessageDTO message){
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getRecipientId()),
                "/hotel/private",
                message);

    }


}
