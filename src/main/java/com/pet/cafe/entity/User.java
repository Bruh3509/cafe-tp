package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    @Column(name = "passport_id")
    String passportId;

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @OneToMany(mappedBy = "user")
    Set<Booking> bookings = new HashSet<>();
}
