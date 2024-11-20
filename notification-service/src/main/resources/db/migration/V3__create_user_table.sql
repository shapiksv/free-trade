CREATE TABLE IF NOT EXISTS user_customer (
    id                   SERIAL PRIMARY KEY NOT NULL,
    first_name           TEXT,
    second_name          TEXT,
    email                TEXT
);

ALTER TABLE message
    ADD CONSTRAINT message_user_id FOREIGN KEY (user_id) REFERENCES user_customer (id);