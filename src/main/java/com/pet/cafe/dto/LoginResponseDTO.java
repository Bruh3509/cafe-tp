package com.pet.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    private String token;

    private long expiresIn;

}