create table Bookings_Guests(
    guest_id varchar(30),
    booking_id bigint,
    primary key(guest_id, booking_id),
    foreign key (guest_id) references Guests(phone_number),
    foreign key (booking_id) references Bookings(booking_id)
);