create table "event"
(
    id            serial        not null,
    id_promoter   int           not null,
    name          varchar(255)  not null,
    description   varchar(255),
    ticket_amount int           not null,
    ticket_value  numeric(9, 2) not null,
    image         bytea,
    event_date    date,
    primary key (id),
    constraint fk_event_promoter foreign key (id_promoter) references "promoter" (id)
)