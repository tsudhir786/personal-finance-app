CREATE TABLE categories
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT uq_categories_name UNIQUE (name),
    CONSTRAINT chk_categories_name_not_blank
        CHECK ( BTRIM(name) <> '' )
);