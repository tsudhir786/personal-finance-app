CREATE TABLE expenses (
                          id BIGINT PRIMARY KEY,
                          description VARCHAR(255) NOT NULL,
                          amount DECIMAL(10, 2) NOT NULL
);
