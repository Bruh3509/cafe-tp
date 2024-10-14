alter table booking_guest drop foreign key booking_guest_ibfk_2;

alter table booking
change column booking_id booking_id bigint not null AUTO_INCREMENT;

alter table booking_guest
add constraint booking_guest_ibfk_2
foreign key (booking_id) references booking (booking_id);

