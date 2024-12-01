set foreign_key_checks = 0;

alter table booking drop foreign key booking_ibfk_2;

alter table user drop primary key;

alter table booking modify column user_id bigint;

alter table user add column user_id bigint auto_increment primary key first;

alter table booking add constraint booking_ibfk_2 foreign key (user_id) references user(user_id);

set foreign_key_checks = 1;