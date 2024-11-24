package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter

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

    public Booking(long bookingId, Date dateFrom, Date dateTo, User user, Room room) {
        this.bookingId = bookingId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.user = user;
        this.room = room;
    }

    @OneToMany(mappedBy = "booking")
    private List<BookingGuest> bookingGuests = new ArrayList<>();
}