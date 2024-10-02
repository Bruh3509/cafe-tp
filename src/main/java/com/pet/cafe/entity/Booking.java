package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "Guests")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    long bookingId;

    @Column(name = "date_from")
    Date dateFrom;

    @Column(name = "date_to")
    Date dateTo;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "passport_id")
    User user;

    @OneToOne
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    Room room;

}
