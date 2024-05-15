create schema client;

alter schema client owner to postgres;

create table if not exists client (
                                      id SERIAL PRIMARY KEY,
                                      email VARCHAR(100) NOT NULL,
                                      first_name VARCHAR(50) NOT NULL,
                                      surname VARCHAR(100) NOT NULL,
                                      third_name VARCHAR(100),
                                      phone_number VARCHAR(50) UNIQUE NOT NULL,
                                      tax_id VARCHAR(50) UNIQUE NOT NULL,
                                      is_company BOOLEAN,
                                      birth_date DATE NOT NULL
);