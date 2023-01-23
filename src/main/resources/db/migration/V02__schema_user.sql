CREATE TABLE "wiki_user"(
    id SERIAL PRIMARY KEY,
    name varchar(50) not null,
    username varchar(50) not null,
    password varchar not null,
    email varchar(150) not null,
    admin boolean,
    created_at date not null
);