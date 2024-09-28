package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rooms")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Room {
    @Id
    @Column(name = "room_number")
    int roomNumber;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "room_type")
    String roomType;

    @Column(name = "cost_per_night")
    BigDecimal costPerNight;

    @Column(name = "description")
    String description;

    @Column(name = "additional_features")
    String additionalFeatures;

    @OneToOne(mappedBy = "room")
    Booking booking;
}
