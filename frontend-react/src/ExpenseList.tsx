import type { Expense } from "./types/Expense";

type ExpenseListProps = {
  expenses: Expense[];
};

function ExpenseList({ expenses }: ExpenseListProps) {
  return (
    <div>
      <h3>Expense List Component: {expenses.length} expenses</h3>

      {expenses.map((expense) => (
        <p key={expense.id}>
          {expense.description} - ₹{expense.amount} - {expense.categoryName}
        </p>
      ))}
    </div>
  );
}
export default ExpenseList;
