create database UserStore;

create table users(
    id serial primary key not null,
    name charecter not null,
    role_id charecter not null references Role(id)
);

create table role(
    id serial primary key,
    role_name charecter not null
);

create table rules(
    id serial primary key,
    rules_for_role int not null references role(id)
);

create table role_rules(
    id serial primary key,
    rules_id int not null references rules(id),
    role_id int not null references role(id)
);


create table catalogy(
    catalogy_id serial primary key,
    catalogy_name character(30) not null
);


create table attache(
    attached_id serial primary key,
    attached_name text not null
);

create table items(
    item_id serial primary key,
    user_items int unique references users(id),
    attached_name int references attache(attached_id),
    catalogy_inform int references catalogy(catalogy_id)
);

create table states(
    state_id serial primary key,
    state_name int references items(item_id)
);

create table coments(
    comments_id serial primary key,
    comment_words int not null references items(item_id)
);
