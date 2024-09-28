package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Bookings_Guests")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BookingGuest {
    @EmbeddedId
    BookingGuestPK bookingGuestPK;

    @ManyToOne
    @MapsId("guest_id")
    @JoinColumn(name = "guest_id", referencedColumnName = "phone_number")
    Guest guest;

    @ManyToOne
    @MapsId("booking_id")
    @JoinColumn(name = "booking_id", referencedColumnName = "bookingId")
    Booking booking;
}
