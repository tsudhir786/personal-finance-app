        console.log("Finance frontend loaded");

        let expensesData;

        loadExpenses();

        const expenseContainer = 
            document.getElementById("expenses-container");

        function loadExpenses(){
        fetch("http://localhost:8080/api/expenses")
            .then(response => response.json())
            .then(expenses => {

                console.log(expenses);

                    expenses.forEach(expense => {

                    const row = document.createElement("tr");

                    row.innerHTML = `
                        <td>${expense.description}</td>
                        <td>₹${expense.amount}</td>
                        <td>${expense.categoryName}</td>
            `;

                        expenseContainer.appendChild(row);

                });
            })
            .catch(error => {
                console.error("Error fetching expenses:", error);
            });
        }


        expenseContainer.textContent = "Expenses will appear here"


        loadCategories();

        const categorySelect = 
        document.getElementById('category');

        function loadCategories(){

            fetch("http://localhost:8080/api/categories")
                .then(response => response.json())
                .then(categories => {

                    console.log(categories);

                    categories.forEach(category => {

                        const option =
                            document.createElement("option");

                            option.value = category.id;
                            option.textContent = category.name;

                            categorySelect
                            .appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error fetching categories: ", error);
                });
        }


        const expenseForm = document.getElementById("expense-form");

        expenseForm.addEventListener("submit", function(event) {
            event.preventDefault();

            const description = 
                document.getElementById("description").value;

            const amount = 
                document.getElementById("amount").value;

            const categoryId =
                document.getElementById("category").value;

            const request = {
                description: description,
                amount: Number(amount),
                categoryId: Number(categoryId)
            };

            fetch("http://localhost:8080/api/expenses", {
                    method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(request)
            })
                .then(response => {
                    console.log("Status:", response.status);
                    return response.json();
                })
                .then(createdExpense => {
                    console.log("Created expense:", createdExpense);
                })
                .catch(error => {
                    console.error("Error creating expense:", error);
                });

            console.log(request);
        })



