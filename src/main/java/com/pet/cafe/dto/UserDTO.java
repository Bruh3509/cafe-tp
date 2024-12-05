package com.pet.cafe.dto;

public record UserDTO(String email,
                      String firstName,
                      String secondName,
                      String lastName,
                      String phoneNumber,
                      String password,
                      String username) {
}