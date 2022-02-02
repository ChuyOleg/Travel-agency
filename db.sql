CREATE TABLE IF NOT EXISTS roles(
  id SERIAL PRIMARY KEY ,
  role varchar(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    password VARCHAR(256) NOT NULL ,
    first_name VARCHAR(32) NOT NULL ,
    last_name VARCHAR(32) NOT NULL ,
    email VARCHAR(128) NOT NULL UNIQUE ,
    money numeric NOT NULL DEFAULT 0,
    role_id INTEGER REFERENCES roles(id) NOT NULL ,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS status(
    id SERIAL PRIMARY KEY ,
    status VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tour_types(
    id SERIAL PRIMARY KEY ,
    type VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS hotel_types(
    id SERIAL PRIMARY KEY ,
    type varchar(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS locations(
    id SERIAL PRIMARY KEY ,
    country VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS cities(
    id SERIAL PRIMARY KEY ,
    city VARCHAR(128) NOT NULL ,
    country_id INTEGER REFERENCES locations(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS tours(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(256) NOT NULL UNIQUE ,
    price numeric NOT NULL ,
    location_id INTEGER REFERENCES locations(id) NOT NULL ,
    description TEXT NOT NULL ,
    max_discount INTEGER NOT NULL DEFAULT 0 CHECK (max_discount >= 0 and max_discount < 100) ,
    discount_step DOUBLE PRECISION NOT NULL DEFAULT 0 CHECK (discount_step >= 0) ,
    tour_type_id INTEGER REFERENCES tour_types(id) NOT NULL ,
    hotel_type_id INTEGER REFERENCES hotel_types(id) NOT NULL ,
    person_number INTEGER NOT NULL CHECK ( person_number > 0 ) ,
    start_date DATE NOT NULL ,
    end_date DATE NOT NULL CHECK ( end_date > tours.start_date ) ,
    nights_number INTEGER NOT NULL ,
    is_burning BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY ,
    user_id INTEGER REFERENCES users(id) NOT NULL ,
    tour_id INTEGER REFERENCES tours(id) NOT NULL ,
    status_id INTEGER REFERENCES status(id) NOT NULL ,
    creation_date DATE NOT NULL ,
    final_price numeric NOT NULL
);

