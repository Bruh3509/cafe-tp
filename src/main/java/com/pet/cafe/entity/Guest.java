package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Guests")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Guest {
    @Id
    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "last_name")
    String lastName;

    @OneToMany(mappedBy = "guest")
    private List<BookingGuest> bookingGuests = new ArrayList<>();
}
