import "./App.css";
import { useEffect, useState } from "react";

function App() {
  type Expense = {
    id: number;
    description: string;
    amount: number;
    categoryId: number;
    categoryName: string;
  };

  type Category = {
    id: number;
    name: string;
  };

  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState("");
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/expenses")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setExpenses(data);
      })
      .catch((error) => {
        console.log("Error fetching expenses: ", error);
      });
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/api/categories")
      .then((response) => response.json())
      .then((data) => {
        setCategories(data);
      });
  }, []);

  return (
    <div>
      <h1>Personal Finance App</h1>
      <h2>Expenses</h2>

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

      {expenses.map((expense) => (
        <p key={expense.id}>
          {expense.description} - ₹{expense.amount} - {expense.categoryName}
        </p>
      ))}
    </div>
  );
}

export default App;
