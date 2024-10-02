package com.pet.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

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
}
