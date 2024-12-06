package com.pet.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter

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
