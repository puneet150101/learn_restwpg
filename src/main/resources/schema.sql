create table if not exists student(
    sid serial primary key,
    sname varchar NOT NULL,
    sclass int NOT NULL
);
create table if not exists users(
    username varchar not null primary key,
    password varchar not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar not null,
    authority varchar not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index if not exists ix_auth_username on authorities (username,authority);
insert into users values('admin','$2y$12$t9.ccVmfd5bb9F8hnG0Fc.JydCzFKkMn5zYPmEMqX.vimC5q.xaA6',true) on conflict do nothing;
insert into authorities values('admin','ROLE_ADMIN') on conflict do nothing;