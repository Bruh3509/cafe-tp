package com.pet.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Table(name = "Guests")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = false)
@ToString(doNotUseGetters = true, onlyExplicitlyIncluded = true)
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
