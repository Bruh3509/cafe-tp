package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter

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

    public Guest(String phoneNumber, String firstName, String secondName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "guest")
    private List<BookingGuest> bookingGuests = new ArrayList<>();
}
