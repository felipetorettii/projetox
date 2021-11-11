create table "ticket"
(
    id        serial not null,
    id_event  int    not null,
    id_user   int    not null,
    showed_up boolean,
    voted boolean,
    primary key (id),
    constraint fk_ticket_event foreign key (id_event) references "event" (id),
    constraint fk_ticket_user foreign key (id_user) references "user" (id)
)