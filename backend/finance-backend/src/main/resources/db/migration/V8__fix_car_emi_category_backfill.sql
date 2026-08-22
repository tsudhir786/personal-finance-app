UPDATE expenses e
SET category_id = c.id FROM categories c
WHERE e.category_id IS NULL
  AND e.description = 'Car EMI'
  AND c.name = 'EMI/Loans';