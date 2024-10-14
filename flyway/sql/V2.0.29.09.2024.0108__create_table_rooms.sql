create table Rooms(
    room_number int primary key,
    capacity int not null,
    room_type varchar(100) not null,
    cost_per_night decimal(10, 2) not null,
    description varchar(1000) not null,
    additional_features varchar(1000)
)