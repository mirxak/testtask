drop schema if exists public cascade;
drop sequence if exists testtask_seq;
create schema public AUTHORIZATION testuser;
create sequence testtask_seq start 1;

create table customer(
    id bigint PRIMARY KEY default nextval('testtask_seq'),
    name_fio text,
    balance real,
    status int,
    login text,
    password text
);

create table partner_mapping(
    id bigint PRIMARY KEY default nextval('testtask_seq'),
    partner_id text,
    partner_account_id text,
    name_fio text,
    user_img_url text,
    customer_id bigint
);