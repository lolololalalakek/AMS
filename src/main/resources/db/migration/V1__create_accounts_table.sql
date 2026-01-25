CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    account_number BIGINT NOT NULL UNIQUE,
    account_currency VARCHAR(10) NOT NULL,
    account_status VARCHAR(20) NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    balance BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
