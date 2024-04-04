CREATE DATABASE twophase;

\connect twophase;


create table sent_messages(
    id uuid primary key,
    message varchar
);

create table received_messages(
    id uuid primary key
);
