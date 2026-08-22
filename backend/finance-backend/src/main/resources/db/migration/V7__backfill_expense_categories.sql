INSERT INTO categories (name)
VALUES ('Utilities')
ON CONFLICT (name) DO NOTHING;

INSERT INTO categories (name)
VALUES ('EMI/Loans')
ON CONFLICT (name) DO NOTHING;

UPDATE expenses
SET category_id = (
    SELECT id
    FROM categories
    WHERE name = 'Groceries'
)
WHERE category_id IS NULL
  AND description = 'Groceries';

UPDATE expenses
SET category_id = (
    SELECT id
    FROM categories
    WHERE name = 'Utilities'
)
WHERE category_id IS NULL
  AND description IN (
                      'Electricity bill',
                      'Internet Bill',
                      'Mobile Recharge',
                      'Updated Mobile Recharge'
    );

UPDATE expenses
SET category_id = (
    SELECT id
    FROM categories
    WHERE name = 'EMI / Loans'
)
WHERE category_id IS NULL
  AND description = 'Car EMI';

UPDATE expenses
SET category_id = (
    SELECT id
    FROM categories
    WHERE name = 'Miscellaneous'
)
WHERE category_id IS NULL
  AND description = 'Flyway Test Expense';