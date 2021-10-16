create table "promoter"
(
    id      serial not null,
    id_user int    not null,
    photo   bytea,
    primary key (id),
    constraint fk_promoter_user foreign key (id_user) references "user" (id)
);