package com.pet.cafe.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileMessage extends Message{
    private String fileType;
    private String fileName;
    private byte[] fileData;
}
