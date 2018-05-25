CREATE TABLE IF NOT EXISTS sellers (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(15) NOT NULL,
	time_on_app TIMESTAMP NOT NULL,
	phone_number VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS car_kinds (
	id SERIAL PRIMARY KEY,
	kind VARCHAR(15) NOT NULL,
	model VARCHAR(15) NOT NULL,
	mark VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
	id SERIAL PRIMARY KEY,
	price INT NOT NULL,
	year INT NOT NULL,
	car_kind_id INT references car_kinds(id),
	country_of_registration VARCHAR(15) NOT NULL,
	description VARCHAR(15) NOT NULL,
	time_on_app TIMESTAMP NOT NULL,
	seller_id INT references sellers(id)
);