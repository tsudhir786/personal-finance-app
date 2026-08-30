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

    

    expenseContainer.textContent = "Expenses will appear here"


    loadCategories();
    


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

    const categorySelect = 
    document.getElementById('category');

  

