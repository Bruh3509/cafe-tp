create table socket_session(
    session_id varchar(20) primary key,
    user_id bigint,
    foreign key (user_id) references user(user_id)
);