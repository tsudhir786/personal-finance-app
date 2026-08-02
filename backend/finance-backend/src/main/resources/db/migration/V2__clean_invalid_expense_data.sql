UPDATE expenses
SET description =
        CASE
            WHEN description IS NULL OR BTRIM(description) = ''
                THEN 'Legacy Expense'
            ELSE BTRIM(description)
            END,
    amount =
        CASE
            WHEN amount IS NULL THEN 1.00
            WHEN amount <= 0 THEN ABS(amount)
            ELSE amount
            END
WHERE description IS NULL
   OR BTRIM(description) = ''
   OR amount IS NULL
   OR amount <= 0;