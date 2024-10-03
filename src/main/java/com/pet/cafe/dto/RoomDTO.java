package com.pet.cafe.dto;

import java.math.BigDecimal;

public record RoomDTO(int capacity,
                      String roomType,
                      BigDecimal costPerNight,
                      String description,
                      String additionalFeatures) {
}
