create table tax_master
(
    id              bigserial,
    tax_name        text,
    status          text,
    created_at      timestamp,
    updated_at      timestamp,
    outlet_id       bigint,
    primary key(id,outlet_id)
);

create index tax_master_index on tax_master(id,tax_name, status);

create table tax_master_sequence
(
    tax_master_id       bigint,
    outlet_id           bigint,
    primary key(outlet_id)
);

create index tax_master_sequence_index on tax_master_sequence(tax_master_id,outlet_id);
