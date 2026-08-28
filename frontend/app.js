console.log("Finance frontend loaded");

let expensesData;

const expenseContainer = 
        document.getElementById("expenses-container");

fetch("http://localhost:8080/api/expenses")
    .then(response => response.json())
    .then(expenses => {

        console.log(expenses);

        expenses.forEach(expense => {

            const row = document.createElement("tr");
            
            const expenseElement =
                document.createElement("p");

                expenseElement.textContent =
                    `${expense.description} - ₹${expense.amount} - ${expense.categoryName}`;

                expenseContainer.appendChild(expenseElement);

        });
    })
    .catch(error => {
        console.error("Error fetching expenses:", error);
    });

    

    expenseContainer.textContent = "Expenses will appear here"