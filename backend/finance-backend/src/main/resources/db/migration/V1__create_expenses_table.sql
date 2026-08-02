CREATE TABLE expenses (
                          id BIGSERIAL PRIMARY KEY,
                          description VARCHAR(255) NOT NULL,
                          amount NUMERIC(12, 2) NOT NULL
);