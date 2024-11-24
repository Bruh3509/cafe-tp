package com.pet.cafe.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {
    private long messageId;
    private String text;
    private long senderId;
    private long recipientId;
    private String fileType;
    private String fileName;
    private byte[] fileData;
}
