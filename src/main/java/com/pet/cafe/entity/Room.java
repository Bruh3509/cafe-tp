package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
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

    public Room(int roomNumber, int capacity, String roomType, BigDecimal costPerNight, String description, String additionalFeatures) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomType = roomType;
        this.costPerNight = costPerNight;
        this.description = description;
        this.additionalFeatures = additionalFeatures;
    }

    @Column(name = "additional_features")
    String additionalFeatures;

    @OneToOne(mappedBy = "room")
    Booking booking;
}
