package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter

public class BookingGuest {
    @EmbeddedId
    BookingGuestPK bookingGuestPK;

    @ManyToOne
    @MapsId("guest_id")
    @JoinColumn(name = "guest_id", referencedColumnName = "phone_number")
    Guest guest;

    @ManyToOne
    @MapsId("booking_id")
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    Booking booking;
}
