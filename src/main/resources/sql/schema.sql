create table if not exists users_cinema (
    id serial not null,
    email varchar(80) not null,
    first_name varchar(16) not null,
    last_name varchar(16) not null,
    phone_number varchar(11) not null,
    password varchar(80) not null,
    pic_name varchar(80),
    loginfo text[]
);
