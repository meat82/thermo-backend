DROP TABLE IF EXISTS temperatures;

CREATE TABLE temperatures (
	temperature_id serial PRIMARY KEY,
	temperature_value NUMERIC(5,2) NOT NULL,
	posting_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	temperature_note VARCHAR (255)
);