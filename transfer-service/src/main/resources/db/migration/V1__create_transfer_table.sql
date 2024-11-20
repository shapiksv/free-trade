CREATE TABLE IF NOT EXISTS transfer (
    id                       SERIAL PRIMARY KEY NOT NULL,
    sender_user_id           BIGINT,
    receiver_user_id         BIGINT,
    item_id                  BIGINT,
    description              VARCHAR(100),
    created_at               TIMESTAMP WITH TIME ZONE,
    updated_at               TIMESTAMP WITH TIME ZONE,
    status                   VARCHAR(36),
    type                     VARCHAR(36),
    amount                   numeric(21, 4)
);