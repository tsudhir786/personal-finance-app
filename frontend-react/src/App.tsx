import heroImg from "./assets/hero.png";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
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

  const [expenses, setExpenses] = useState<Expense[]>([]);

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

  return (
    <div>
      <h1>Personal Finance App</h1>
      <h2>Expenses</h2>

      {expenses.map((expense) => (
        <p key={expense.id}>
          {expense.description} - ₹{expense.amount} - {expense.categoryName}
        </p>
      ))}
    </div>
  );
}

export default App;
