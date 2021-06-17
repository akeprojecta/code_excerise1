create table USER
(
    id       bigserial primary key,
    username varchar(45),
    password varchar(45)
);

create table EMPLOYEE
(
    id bigserial primary key,
    name   varchar(45),
    surname   varchar(45)
);