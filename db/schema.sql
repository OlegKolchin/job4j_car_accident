CREATE TABLE accident_type(
    id serial primary key,
    name text
);

CREATE TABLE accident (
    id serial primary key,
    name text,
    description text,
    address text,
    accident_type_id int references accident_type(id)
);

CREATE TABLE rules(
    id serial primary key,
    name text
);

CREATE TABLE accident_rules(
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references rules(id)
);