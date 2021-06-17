create table USER
(
    id       bigserial primary key,
    username varchar(255),
    password varchar(255)
);

create table EMPLOYEE
(
    id bigserial primary key,
    name   varchar(255),
    surname   varchar(255)
);