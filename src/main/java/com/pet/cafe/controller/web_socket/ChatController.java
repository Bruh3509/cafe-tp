package com.pet.cafe.controller.web_socket;

import com.pet.cafe.MessageSender;
import com.pet.cafe.dto.SocketSessionDTO;
import com.pet.cafe.dto.message.MessageDTO;
import com.pet.cafe.service.MessageService;
import com.pet.cafe.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ChatController {
    MessageSender sender;
    UserService userService;
    MessageService messageService;

    @MessageMapping("/chat/public")
    @SendTo("/hotel/online")
    public MessageDTO receivePublicMessage(@Payload MessageDTO message){
        return message;
    }

    @MessageMapping("/chat/private")
    public MessageDTO receivePrivateMessage(@Payload MessageDTO message){
        long recipientID = message.getRecipientId();
        Set<SocketSessionDTO> sessions = userService.getSessions(recipientID);
        if(sessions.isEmpty()){
            messageService.addMessage(message);
        }
        else{
            sender.sendMessageToUser(message);
        }

        return message;
    }
}
