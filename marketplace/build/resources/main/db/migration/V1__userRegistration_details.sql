create table user_details
(
    id                  bigserial,
    name                text not null,
    email               text,
    set_password        text not null,
    confirm_password    text,
    status              text,
    createdat           timestamp,
    updatedat           timestamp,
    primary key (email,id)
);

create index user_details_idx on user_details(id, name, email);

