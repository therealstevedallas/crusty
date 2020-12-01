drop table if exists account;
drop sequence if exists hibernate_sequence;


create sequence hibernate_sequence cache 20;

create table account (
id bigint not null primary key default nextval('hibernate_sequence'),
name varchar(100) not null,
passwd varchar(100) not null,
last_name varchar(100) null,
first_name varchar(100) null,
description varchar(512) null,
active char(1) not null default 'Y',
created timestamp not null default now(),
last_login timestamp not null default now()
);

create unique index idx_account on account (name);

insert into account (id, name, passwd, last_name, first_name) values (nextval('hibernate_sequence'), 'walterj', 'ThVLkV3vn8hX6','Walter', 'Jordan');

select * from account;

select * from account where name = 'walterj';