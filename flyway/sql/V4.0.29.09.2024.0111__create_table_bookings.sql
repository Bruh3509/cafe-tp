create table Bookings(
    booking_id bigint primary key,
    room_number int,
    user_id varchar(15),
    date_from date not null,
    date_to date not null,
    foreign key (room_number) references Rooms(room_number),
    foreign key (user_id) references Users(passport_id)
);