CREATE TABLE "wiki_volunteers"(
    id SERIAL PRIMARY KEY,
    name varchar(50) not null,
    email varchar(150) not null,
    phone varchar(50) not null,
    street varchar(150) not null,
    neighborhood varchar(150) not null,
    city varchar(150) not null,
    state varchar(150) not null,
    number varchar(50),
    complement varchar(200),
    cep varchar(50) not null,
    photo varchar,
    description varchar,
    created_at date not null
);

CREATE TABLE "wiki_post_share"(
    id SERIAL PRIMARY KEY,
    share varchar not null,
    link_share varchar not null,
    created_at date not null
);