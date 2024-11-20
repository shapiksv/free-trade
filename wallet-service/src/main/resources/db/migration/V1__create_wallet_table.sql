CREATE TABLE IF NOT EXISTS mutable_part_of_wallet (
    id                     SERIAL PRIMARY KEY NOT NULL,
    wallet_Id              BIGINT,
    amount                 numeric(21, 4),
    created_at             TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS wallet (
    id                     SERIAL PRIMARY KEY NOT NULL,
    user_id                BIGINT,
    serial                 VARCHAR(100),
    mutable_part_id        BIGINT constraint fk_wallet_mutable_part_of_wallet_id
        references mutable_part_of_wallet,
    currency               VARCHAR(100),
    created_at             TIMESTAMP WITH TIME ZONE,
    version                BIGINT
);