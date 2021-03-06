DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS tours;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS tour_types;
DROP TABLE IF EXISTS hotel_types;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS roles(
    role_id SERIAL PRIMARY KEY ,
    role varchar(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    password VARCHAR(256) NOT NULL ,
    first_name VARCHAR(32) NOT NULL ,
    last_name VARCHAR(32) NOT NULL ,
    email VARCHAR(128) NOT NULL UNIQUE ,
    money numeric NOT NULL DEFAULT 0,
    role_id INTEGER REFERENCES roles(role_id) NOT NULL ,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS status(
    status_id SERIAL PRIMARY KEY ,
    status VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tour_types(
    tour_type_id SERIAL PRIMARY KEY ,
    tour_type VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS hotel_types(
    hotel_type_id SERIAL PRIMARY KEY ,
    hotel_type varchar(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS countries(
    country_id SERIAL PRIMARY KEY ,
    country VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS cities(
    city_id SERIAL PRIMARY KEY ,
    city VARCHAR(128) NOT NULL ,
    country_id INTEGER REFERENCES countries(country_id) NOT NULL,
    UNIQUE (city, country_id)
);

CREATE TABLE IF NOT EXISTS tours(
    tour_id SERIAL PRIMARY KEY ,
    name VARCHAR(256) NOT NULL UNIQUE ,
    price numeric NOT NULL ,
    city_id INTEGER REFERENCES cities(city_id) NOT NULL ,
    description TEXT NOT NULL ,
    max_discount INTEGER NOT NULL DEFAULT 0 CHECK (max_discount >= 0 and max_discount < 100) ,
    discount_step DOUBLE PRECISION NOT NULL DEFAULT 0 CHECK (discount_step >= 0) ,
    tour_type_id INTEGER REFERENCES tour_types(tour_type_id) NOT NULL ,
    hotel_type_id INTEGER REFERENCES hotel_types(hotel_type_id) NOT NULL ,
    person_number INTEGER NOT NULL CHECK ( person_number > 0 ) ,
    start_date DATE NOT NULL ,
    end_date DATE NOT NULL CHECK ( end_date > tours.start_date ) ,
    nights_number INTEGER NOT NULL ,
    is_burning BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    order_id SERIAL PRIMARY KEY ,
    user_id INTEGER REFERENCES users(user_id) NOT NULL ,
    tour_id INTEGER REFERENCES tours(tour_id) NOT NULL ,
    status_id INTEGER REFERENCES status(status_id) NOT NULL ,
    creation_date DATE NOT NULL ,
    final_price numeric NOT NULL ,
    UNIQUE (user_id, tour_id)
);


INSERT INTO roles (role) VALUES ('USER');
INSERT INTO roles (role) VALUES ('MANAGER');
INSERT INTO roles (role) VALUES ('ADMIN');

INSERT INTO status (status) VALUES ('REGISTERED');
INSERT INTO status (status) VALUES ('PAID');
INSERT INTO status (status) VALUES ('CANCELED');

INSERT INTO tour_types (tour_type) VALUES ('VACATION');
INSERT INTO tour_types (tour_type) VALUES ('EXCURSION');
INSERT INTO tour_types (tour_type) VALUES ('SHOPPING');

INSERT INTO hotel_types (hotel_type) VALUES ('ONE_STAR');
INSERT INTO hotel_types (hotel_type) VALUES ('TWO_STARS');
INSERT INTO hotel_types (hotel_type) VALUES ('THREE_STARS');
INSERT INTO hotel_types (hotel_type) VALUES ('FOUR_STARS');
INSERT INTO hotel_types (hotel_type) VALUES ('FIVE_STARS');

INSERT INTO countries (country) VALUES ('Spain');
INSERT INTO countries (country) VALUES ('France');
INSERT INTO countries (country) VALUES ('Egypt');
INSERT INTO countries (country) VALUES ('Austria');
INSERT INTO countries (country) VALUES ('Portugal');
INSERT INTO countries (country) VALUES ('Turkey');

INSERT INTO cities (city, country_id) VALUES ('Madrid', (SELECT country_id FROM countries WHERE country = 'Spain'));
INSERT INTO cities (city, country_id) VALUES ('Barcelona', (SELECT country_id FROM countries WHERE country = 'Spain'));
INSERT INTO cities (city, country_id) VALUES ('Seville', (SELECT country_id FROM countries WHERE country = 'Spain'));
INSERT INTO cities (city, country_id) VALUES ('Paris', (SELECT country_id FROM countries WHERE country = 'France'));
INSERT INTO cities (city, country_id) VALUES ('Nice', (SELECT country_id FROM countries WHERE country = 'France'));
INSERT INTO cities (city, country_id) VALUES ('Lyon', (SELECT country_id FROM countries WHERE country = 'France'));
INSERT INTO cities (city, country_id) VALUES ('Cairo', (SELECT country_id FROM countries WHERE country = 'Egypt'));
INSERT INTO cities (city, country_id) VALUES ('Alexandria', (SELECT country_id FROM countries WHERE country = 'Egypt'));
INSERT INTO cities (city, country_id) VALUES ('Vienna', (SELECT country_id FROM countries WHERE country = 'Austria'));
INSERT INTO cities (city, country_id) VALUES ('Salzburg', (SELECT country_id FROM countries WHERE country = 'Austria'));
INSERT INTO cities (city, country_id) VALUES ('Graz', (SELECT country_id FROM countries WHERE country = 'Austria'));
INSERT INTO cities (city, country_id) VALUES ('Lisbon', (SELECT country_id FROM countries WHERE country = 'Portugal'));
INSERT INTO cities (city, country_id) VALUES ('Porto', (SELECT country_id FROM countries WHERE country = 'Portugal'));
INSERT INTO cities (city, country_id) VALUES ('Istanbul', (SELECT country_id FROM countries WHERE country = 'Turkey'));
INSERT INTO cities (city, country_id) VALUES ('Antalya', (SELECT country_id FROM countries WHERE country = 'Turkey'));

INSERT INTO users (username, password, first_name, last_name, email, role_id, is_blocked) VALUES ('User', '340C5FA76443DF1AA895CC5CBA1CB301', 'Andrew', 'Gosenz', 'andrew.gosenz@gmail.com', (SELECT role_id FROM roles WHERE role = 'USER'), false);
-- password: User2002
INSERT INTO users (username, password, first_name, last_name, email, role_id, is_blocked) VALUES ('Manager', '9B7857D281F29C4B1B9C128BA808D4EC', 'Peter', 'Kolin', 'peter.kolin@gmail.com', (SELECT role_id FROM roles WHERE role = 'MANAGER'), false);
-- password: Manager2002
INSERT INTO users (username, password, first_name, last_name, email, role_id, is_blocked) VALUES ('Admin', 'AAA2192BF80B184FD94B22A9F68ADD5C', 'Joe', 'Pistone', 'joe.pistone@gmail.com', (SELECT role_id FROM roles WHERE role = 'ADMIN'), false);
-- password: Admin2002

iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Sunrise and Sunset Trips', 1100, (SELECT city_id FROM cities WHERE city = 'Madrid'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 15, 3, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'EXCURSION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FOUR_STARS'), 2, '2022-04-15', '2022-04-19', 4, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Scenic Trip', 2000, (SELECT city_id FROM cities WHERE city = 'Barcelona'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 10, 1, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 2, '2022-04-25', '2022-04-30', 5, true);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Here and There Tours', 1400, (SELECT city_id FROM cities WHERE city = 'Seville'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 12, 0.5, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'SHOPPING'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FOUR_STARS'), 1, '2022-04-20', '2022-04-26', 6, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('VIP Luxury Trip', 3500, (SELECT city_id FROM cities WHERE city = 'Paris'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 8, 0.2, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'SHOPPING'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 3, '2022-04-15', '2022-04-22', 2, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Urban Explorer Tour', 2700, (SELECT city_id FROM cities WHERE city = 'Nice'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 20, 2, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 4, '2022-04-21', '2022-04-28', 7, true);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Travel Rhythm', 1900, (SELECT city_id FROM cities WHERE city = 'Lyon'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 16, 2, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FOUR_STARS'), 3, '2022-04-24', '2022-04-29', 5, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Living on the Edge', 1900, (SELECT city_id FROM cities WHERE city = 'Cairo'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 24, 3, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'EXCURSION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 2, '2022-04-22', '2022-04-27', 5, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Dazzling Tour', 1400, (SELECT city_id FROM cities WHERE city = 'Alexandria'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 30, 3, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'THREE_STARS'), 4, '2022-04-16', '2022-04-23', 7, true);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Weekend Getaway', 550, (SELECT city_id FROM cities WHERE city = 'Salzburg'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 5, 1, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'EXCURSION'),(SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FOUR_STARS'), 2, '2022-04-22', '2022-04-24', 2, true);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Vacation As A Lifestyle', 999, (SELECT city_id FROM cities WHERE city = 'Lisbon'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 8, 1, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'THREE_STARS'), 2, '2022-04-14', '2022-04-20', 6, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('SightCity travel', 600, (SELECT city_id FROM cities WHERE city = 'Porto'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 12, 2, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'SHOPPING'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FOUR_STARS'), 2, '2022-04-21', '2022-04-24', 3, true);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Uncommon Travel', 1300, (SELECT city_id FROM cities WHERE city = 'Istanbul'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 14, 2, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'VACATION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 1, '2022-04-18', '2022-04-24', 6, false);
iNSERT INTO tours (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id, person_number, start_date, end_date, nights_number, is_burning) VALUES ('Life Changing Travel', 1200, (SELECT city_id FROM cities WHERE city = 'Antalya'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 15, 3, (SELECT tour_type_id FROM tour_types WHERE tour_type = 'EXCURSION'), (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = 'FIVE_STARS'), 2, '2022-04-20', '2022-04-24', 4, false);

