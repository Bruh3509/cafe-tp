package com.pet.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BookingGuestPK {
    @Column(name = "guest_id")
    String guestId;

    @Column(name = "booking_id")
    long bookingId;
}
