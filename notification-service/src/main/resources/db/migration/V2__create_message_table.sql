CREATE TABLE IF NOT EXISTS message (
    id                   SERIAL PRIMARY KEY NOT NULL,
    user_id               BIGINT,
    contact              TEXT,
    subject              TEXT,
    email                TEXT,
    text                 TEXT,
    created_at           TIMESTAMP WITH TIME ZONE
);
