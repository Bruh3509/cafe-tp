package com.pet.cafe.controller.web_socket;

import com.pet.cafe.MessageSender;
import com.pet.cafe.dto.message.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ChatController {
    MessageSender sender;

    @MessageMapping("/chat/public")
    public void receivePublicMessage(@Payload Message message){
        sender.sendMessageToOnlineUsers(message);
    }

    @MessageMapping("/chat/private")
    public void receivePrivateMessage(@Payload Message message){
        //method for sending message to specified user
    }
}
