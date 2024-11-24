package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "password")
    String password;

    @Column(name = "username")
    String username;

    public User(String passportId, String email, String firstName, String secondName, String lastName, String phoneNumber, String password, String username) {
        this.passportId = passportId;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.username = username;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    Set<Booking> bookings = new HashSet<>();


    @OneToMany(mappedBy = "user")
    Set<SocketSession> sessions = new HashSet<>();

//    @OneToMany(mappedBy = "sender", orphanRemoval = true)
//    Set<Message> sentMessages = new HashSet<>();
//
//    @OneToMany(mappedBy = "recipient", orphanRemoval = true)
//    Set<Message> receivedMessages = new HashSet<>();
}
