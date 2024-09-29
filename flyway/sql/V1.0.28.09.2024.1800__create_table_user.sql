create table Users(
    passport_id varchar(15) primary key,
    email varchar(255) not null,
    first_name varchar(100) not null,
    second_name varchar(100) not null,
    last_name varchar(100),
    phone_number varchar(30) not null
)