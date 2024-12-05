create table message(
    message_id bigint primary key auto_increment,
    sender_id bigint not null,
    recipient_id bigint,
    sent_at timestamp default current_timestamp,
    text varchar(1000) not null,
    file blob,
    file_type varchar(100),
    file_name varchar(100),
    foreign key (sender_id) references user(user_id),
    foreign key (recipient_id) references user(user_id)
);