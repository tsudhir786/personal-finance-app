ALTER TABLE expenses
    ADD CONSTRAINT chk_expenses_description_not_blank
        CHECK ( BTRIM(description) <> '' );

ALTER TABLE expenses
    ADD CONSTRAINT chk_expenses_amount_positive
        CHECK ( amount > 0 );