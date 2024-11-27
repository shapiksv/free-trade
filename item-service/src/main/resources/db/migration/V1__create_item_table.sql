CREATE TABLE IF NOT EXISTS item (
    id                     SERIAL PRIMARY KEY NOT NULL,
    owner_id               BIGINT,
    title                  VARCHAR(100),
    description            VARCHAR(100),
    image_linc             VARCHAR(100),
    created_at             TIMESTAMP WITH TIME ZONE,
    status                 VARCHAR(36),
    price                  numeric(21, 4)
);