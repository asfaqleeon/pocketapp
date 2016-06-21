create table account (
  account_id serial primary key not null,
  email varchar(10) not null UNIQUE,
  password varchar(10) not null
);

CREATE TABLE link (
  link_id serial PRIMARY KEY not null,
  url VARCHAR(200) not null,
  category VARCHAR(20),
  title VARCHAR(200) not null,
  description text,
  account_id int references account(account_id)
);
