set foreign_key_checks = 0;

alter table booking_guest drop foreign key booking_guest_ibfk_1;

alter table booking_guest drop primary key;

alter table guest drop primary key;

alter table booking_guest modify column guest_id bigint;

alter table guest add column guest_id bigint auto_increment primary key first;

alter table booking_guest add constraint booking_guest_ibfk_1 foreign key (guest_id) references guest(guest_id);

alter table booking_guest add constraint booking_guest_pk primary key (guest_id, booking_id);

set foreign_key_checks = 1;
