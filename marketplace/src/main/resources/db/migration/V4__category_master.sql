create table category_master
(
    id              bigserial,
    category_name   text,
    alias           text,
    status          text,
    created_at      timestamp,
    updated_at      timestamp,
    outlet_id       bigint,
    primary key(id,outlet_id)
);

create index category_master_index on category_master(id,category_name,alias, status);

create table category_master_sequence
(
    category_master_id  bigint,
    outlet_id           bigint,
    primary key(outlet_id)
);

create index category_master_sequence_index on category_master_sequence(category_master_id,outlet_id);
