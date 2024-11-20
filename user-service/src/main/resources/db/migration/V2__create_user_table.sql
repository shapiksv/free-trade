CREATE TABLE IF NOT EXISTS user_customer (
    id                   SERIAL PRIMARY KEY NOT NULL,
    username             TEXT,
    first_name           TEXT,
    second_name          TEXT,
    email                TEXT,
    role                 TEXT,
    status               TEXT,
    password             TEXT,
    date_of_birthday    TIMESTAMP WITH TIME ZONE,
    created_at          TIMESTAMP WITH TIME ZONE,
    delete_at           TIMESTAMP WITH TIME ZONE
);


CREATE TABLE IF NOT EXISTS confirmation_code (
    id                   SERIAL PRIMARY KEY NOT NULL,
    email                TEXT,
    code                 TEXT,
    attempt              INTEGER,
    status               TEXT,
    created_at           TIMESTAMP WITH TIME ZONE
);