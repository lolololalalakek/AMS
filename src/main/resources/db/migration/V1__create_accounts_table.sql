CREATE TABLE accounts
(
    id               UUID PRIMARY KEY     DEFAULT gen_random_uuid(),

    user_id          BIGINT      NOT NULL,

    account_number   VARCHAR(20) NOT NULL UNIQUE,

    account_currency VARCHAR(32) NOT NULL,

    account_status   VARCHAR(32) NOT NULL,

    account_type     VARCHAR(32) NOT NULL,

    created_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
