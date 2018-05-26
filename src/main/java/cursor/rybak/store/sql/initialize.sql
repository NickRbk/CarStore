DROP TABLE sellers, cars, car_kinds;

CREATE TABLE IF NOT EXISTS sellers (
	id SERIAL PRIMARY KEY,
	email VARCHAR(100) UNIQUE NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
  password VARCHAR(100) NOT NULL,
	created_at TIMESTAMPTZ DEFAULT now(),
	phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS car_kinds (
	id SERIAL PRIMARY KEY,
	kind VARCHAR(100) NOT NULL,
	model VARCHAR(100) NOT NULL,
	mark VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
	id SERIAL PRIMARY KEY,
	price INT NOT NULL,
	year INT NOT NULL,
	car_kind_id INT REFERENCES car_kinds(id),
	country_of_registration VARCHAR(100) NOT NULL,
	description VARCHAR(255) NOT NULL,
	created_at TIMESTAMPTZ DEFAULT now(),
	seller_id  INT REFERENCES sellers(id)
);