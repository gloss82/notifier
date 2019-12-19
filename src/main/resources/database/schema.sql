create table message_status (
    id integer not null
        constraint message_status_pk
            primary key,
    name varchar(16)
);

comment on table message_status is 'Outcome message status';
comment on column message_status.name is 'status name';
alter table message_status owner to notifier;

create table message_importance (
    id integer not null
        constraint message_importance_pk
            primary key,
    name varchar(16)
);

comment on table message_importance is 'Outcome message importance';
comment on column message_importance.name is 'importance name';
alter table message_importance owner to notifier;

create table message (
    id uuid not null
        constraint messages_pk
            primary key,
    phone char(11) not null,
    created timestamp not null,
    content varchar(1000) not null,
    updated timestamp,
    status integer not null
        constraint message_status__fk
            references message_status,
    importance integer
        constraint message_message_importance_id_fk
            references message_importance
);

comment on table message is 'Outcome messages';
comment on column message.phone is 'recipient phone number';
comment on column message.created is 'create time (UTC)';
comment on column message.content is 'message content';
comment on column message.updated is 'update time (UTC)';
comment on column message.status is 'delivery status';
comment on column message.importance is 'message importance';
alter table message owner to notifier;

create table push_subscription (
    phone char(11) not null
        constraint push_subscription_pk
            primary key,
    created timestamp not null,
    updated timestamp,
    token varchar(255),
    active boolean default true not null
);

create unique index push_subscription_token_uindex
    on push_subscription (token);

comment on table push_subscription is 'push subscription table';
comment on column push_subscription.phone is 'recipient phone number';
comment on column push_subscription.created is 'create date time';
comment on column push_subscription.updated is 'update datetime';
comment on column push_subscription.token is 'push service token';
comment on column push_subscription.active is 'push enable flag';
alter table push_subscription owner to notifier;

