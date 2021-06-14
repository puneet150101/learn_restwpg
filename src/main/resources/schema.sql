create table if not exists student(
    sid serial primary key,
    sname varchar NOT NULL,
    sclass int NOT NULL
);