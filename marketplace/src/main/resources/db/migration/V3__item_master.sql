create table item_master
(
    id          bigserial,
    item_name   text,
    alias       text,
    mrp         double precision,
    sale_rate   double precision,
    stock       double precision,
    category    text,
    gst         double precision,
    tax_type    boolean,
    status      text,
    created_at  timestamp,
    updated_at  timestamp,
    outlet_id   bigint,
    primary key(id,outlet_id)
);

create index item_master_index on item_master(id,item_name,alias,status);

create table item_master_sequence
(
    item_master_id  bigint,
    outlet_id       bigint,
    primary key(outlet_id)
);

create index item_master_sequence_index on item_master_sequence(item_master_id, outlet_id);


