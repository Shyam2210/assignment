CREATE TABLE accounts (
                          account_id       UUID PRIMARY KEY,
                          balance          NUMERIC(20, 5) NOT NULL DEFAULT 0.00,
                          currency         VARCHAR(3) NOT NULL,
                          created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE transactions (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              source_account_id VARCHAR(50) NOT NULL,
                              destination_account_id VARCHAR(50) NOT NULL,
                              amount NUMERIC(20, 5) NOT NULL,
                              currency VARCHAR(10) NOT NULL DEFAULT 'USD',
                              transaction_type VARCHAR(20) NOT NULL, -- e.g., TRANSFER, DEPOSIT, WITHDRAWAL
                              status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- e.g., SUCCESS, FAILED
                              description TEXT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TYPE transaction_type AS ENUM ('credit', 'debit');

-- for performance
CREATE INDEX idx_transactions_account_id ON transactions(account_id);
CREATE INDEX idx_transactions_created_at ON transactions(created_at);