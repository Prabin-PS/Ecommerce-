create table outlet_details
(
    id            bigserial,
    outlet_name   text,
    url           text not null,
    status        text,
    created_at    timestamp,
    updated_at    timestamp,
    primary key (outlet_name,id)

);

create index outlet_details_idx on outlet_details(id, outlet_name, url, status);