drop table if exists customer;

create table customer (
  id bigserial not null primary key,
  names text[]
);
