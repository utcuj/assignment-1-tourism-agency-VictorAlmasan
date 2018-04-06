DROP DATABASE IF EXISTS assignment1;
CREATE DATABASE assignment1;
USE assignment1;

CREATE TABLE IF NOT EXISTS user_types (
    user_type_code VARCHAR(1) PRIMARY KEY NOT NULL,
    user_type_description VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS operation_types (
    operation_type_code VARCHAR(2) PRIMARY KEY NOT NULL,
    operation_type_description VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
	user_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_type_code VARCHAR(1) NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
	CONSTRAINT fk_users_user_types FOREIGN KEY (user_type_code)
        REFERENCES user_types (user_type_code)
);

CREATE TABLE IF NOT EXISTS logging (
	log_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id INT(3) NOT NULL,
    operation_type_code VARCHAR(2) NOT NULL,
    log_date DATE NOT NULL,
    CONSTRAINT fk_logging_user FOREIGN KEY (user_id)
        REFERENCES users (user_id),
	CONSTRAINT fk_logging_operation_types FOREIGN KEY (operation_type_code)
        REFERENCES operation_types (operation_type_code)
);

CREATE TABLE IF NOT EXISTS hotel_ratings (
	hotel_rating_code VARCHAR(1) PRIMARY KEY NOT NULL,
    quality VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS hotels (
	hotel_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    hotel_rating_code VARCHAR(1) NOT NULL,
    location VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    CONSTRAINT fk_hotels_hotel_ratings FOREIGN KEY (hotel_rating_code)
        REFERENCES hotel_ratings (hotel_rating_code)
);

CREATE TABLE IF NOT EXISTS booking_outcomes (
	booking_outcome_code VARCHAR(1) PRIMARY KEY NOT NULL,
    booking_outcome_description VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
	customer_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    cnp VARCHAR(13) NOT NULL UNIQUE,
    address VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
	booking_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	booking_outcome_code VARCHAR(1) NOT NULL,
	hotel_id INT(3) NOT NULL,
    main_customer_id INT(3) NOT NULL,
    total_price INT(10) NOT NULL,
    final_payment_date DATE NOT NULL,
    CONSTRAINT fk_bookings_booking_outcomes FOREIGN KEY (booking_outcome_code)
        REFERENCES booking_outcomes (booking_outcome_code),
	CONSTRAINT fk_bookings_hotels FOREIGN KEY (hotel_id)
        REFERENCES hotels (hotel_id),
	CONSTRAINT fk_bookings_customers FOREIGN KEY (main_customer_id)
        REFERENCES customers (customer_id)
);

CREATE TABLE IF NOT EXISTS payments (
	payment_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    booking_id INT(3) NOT NULL,
    payment_amount INT(10) NOT NULL,
    payment_date DATE,
    CONSTRAINT fk_payments_bookings FOREIGN KEY (booking_id)
        REFERENCES bookings (booking_id)
);

CREATE TABLE IF NOT EXISTS tourists (
	tourist_id INT(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    customer_id INT(3) NOT NULL,
    booking_id INT(3) NOT NULL,
	CONSTRAINT fk_tourists_customers FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id),
	CONSTRAINT fk_tourists_bookings FOREIGN KEY (booking_id)
        REFERENCES bookings (booking_id)
);



-- -- INSERT INTO TABLES -- ---


-- user_types
INSERT INTO user_types VALUES ("A","Administrator");
INSERT INTO user_types VALUES ("U","User");

-- operation_types
INSERT INTO operation_types VALUES ("AC","Add Customer");
INSERT INTO operation_types VALUES ("UC","Update Customer");
INSERT INTO operation_types VALUES ("VC","View Customer");
INSERT INTO operation_types VALUES ("AB","Add Booking");
INSERT INTO operation_types VALUES ("UB","Update Booking");
INSERT INTO operation_types VALUES ("VB","View Booking");
INSERT INTO operation_types VALUES ("DB","Delete Booking");
INSERT INTO operation_types VALUES ("AP","Add (Partial)Payment");
INSERT INTO operation_types VALUES ("VM","View Clients Who Missed Final Payment Deadline");

-- users
INSERT INTO users (username, password, user_type_code) VALUES ("admin", "admin2018", "A");
INSERT INTO users (username, password, user_type_code) VALUES ("user", "user2018", "U");
INSERT INTO users (username, password, user_type_code) VALUES ("user1", "user", "U");
INSERT INTO users (username, password, user_type_code) VALUES ("user2", "userpass", "U");

-- hotel_ratings
INSERT INTO hotel_ratings VALUES ("1", "*");
INSERT INTO hotel_ratings VALUES ("2", "**");
INSERT INTO hotel_ratings VALUES ("3", "***");
INSERT INTO hotel_ratings VALUES ("4", "****");
INSERT INTO hotel_ratings VALUES ("5", "*****");

-- hotels
INSERT INTO hotels (location, name, hotel_rating_code) VALUES ("Cluj-Napoca", "Napoca", "4");
INSERT INTO hotels (location, name, hotel_rating_code) VALUES ("Bucuresti", "Belvedere", "5");
INSERT INTO hotels (location, name, hotel_rating_code) VALUES ("Timisoara", "Banat", "4");
INSERT INTO hotels (location, name, hotel_rating_code) VALUES ("Iasi", "Moldoveanul", "4");

-- booking_outcomes
INSERT INTO booking_outcomes VALUES ("C","Cancelled");
INSERT INTO booking_outcomes VALUES ("P","In progress");
INSERT INTO booking_outcomes VALUES ("O","OK");

-- customers
INSERT INTO customers (name, cnp, address) VALUES ("Muresan Liviu", "1900221191233", "21 Dec. CJ");
INSERT INTO customers (name, cnp, address) VALUES ("Pop Sergiu", "1911205118048", "Aurel Vlaicu CJ");
INSERT INTO customers (name, cnp, address) VALUES ("Banciu Andrei", "1811215114326", "Ialomitei Buc.");
INSERT INTO customers (name, cnp, address) VALUES ("David Flaviu", "1920311981821", "Pipera Buc.");


-- bookings
INSERT INTO bookings(booking_outcome_code, hotel_id, main_customer_id, total_price, final_payment_date) VALUES
('P', 1, 1, 150, '2019-03-10');
INSERT INTO bookings(booking_outcome_code, hotel_id, main_customer_id, total_price, final_payment_date) VALUES
('P', 1, 2, 152, '2022-03-10');

-- payments
INSERT INTO payments(booking_id, payment_amount, payment_date) VALUES (1, 50, '2018-02-02');
INSERT INTO payments(booking_id, payment_amount, payment_date) VALUES (1, 55, '2018-03-02');

INSERT INTO payments(booking_id, payment_amount, payment_date) VALUES (2, 56, '2018-02-01');