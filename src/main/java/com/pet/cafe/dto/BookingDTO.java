package com.pet.cafe.dto;

import java.util.Date;

public record BookingDTO(Date dateFrom, Date dateTo, String userId, long roomNumber) {
}
