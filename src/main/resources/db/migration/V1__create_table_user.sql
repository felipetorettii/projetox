create table "user"
(
    id    serial       not null,
    name  varchar(255) not null,
    email varchar(255) not null,
    pass  varchar(255) not null,
    primary key (id)
);