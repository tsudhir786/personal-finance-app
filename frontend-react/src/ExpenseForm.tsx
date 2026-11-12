import type { SubmitEvent } from "react";
import type { Category } from "./types/Category";

type ExpenseFormProps = {
  description: string;
  setDescription: (value: string) => void;

  amount: string;
  setAmount: (value: string) => void;

  categories: Category[];
  selectedCategoryId: string;
  setSelectedCategoryId: (value: string) => void;

  onSubmit: (event: SubmitEvent<HTMLFormElement>) => void;
};

function ExpenseForm({
  description,
  setDescription,
  amount,
  setAmount,
  categories,
  selectedCategoryId,
  setSelectedCategoryId,
  onSubmit,
}: ExpenseFormProps) {
  return (
    <form onSubmit={onSubmit}>
      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(event) => setDescription(event.target.value)}
      />
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={(event) => setAmount(event.target.value)}
      />
      <select
        value={selectedCategoryId}
        onChange={(event) => setSelectedCategoryId(event.target.value)}
      >
        <option value="">Select Category</option>

        {categories.map((category) => (
          <option key={category.id} value={category.id}>
            {category.name}
          </option>
        ))}
      </select>
      <button id="add-expense-button" type="submit">
        Add Expense
      </button>
    </form>
  );
}

export default ExpenseForm;
