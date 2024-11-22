const books = [
    { title: "The Alchemist", category: "Fiction" },
    { title : "To Kill a Mockingbird", category: "Fiction"},
    { title : "The Catcher in the Rye", category: "Fiction"},
    { title: "A Brief History of Time", category: "Science" },
    { title: "Cosmos", category: "Science" },
    { title: "The Origin of Species", category: "Science"},
    { title: "National Geographic", category: "Magazines" },
    { title: "Harry Potter", category: "Novels" },
    { title: "Pilgrim's Progress", category: "Novels" },
    { title: "Frankenstein", category: "Novels" },
    { title: "Learning JavaScript", category: "Technology" },
  ];
  
  // Registration
  document.getElementById("registerForm")?.addEventListener("submit", (e) => {
    e.preventDefault();
    const name = document.getElementById("name").value;
    const age = parseInt(document.getElementById("age").value, 10);
    const email = document.getElementById("email").value;
    const categories = Array.from(
      document.querySelectorAll('input[type="checkbox"]:checked')
    ).map((cb) => cb.value);
  
    if (age < 10 || age > 80) {
      alert("Age must be between 10 and 80 years.");
      return;
    }
  
    const user = { name, age, email, categories };
    localStorage.setItem(email, JSON.stringify(user));
    alert("Registration successful!");
    window.location.href = "index.html";
  });
  
  // Login
  document.getElementById("loginForm")?.addEventListener("submit", (e) => {
    e.preventDefault();
    const email = document.getElementById("email").value;
    const user = JSON.parse(localStorage.getItem(email));
  
    if (!user) {
      alert("User not found. Please register first.");
    } else {
      localStorage.setItem("currentUser", email);
      window.location.href = "dashboard.html";
    }
  });
  
  // Dashboard
  window.onload = () => {
    const currentUserEmail = localStorage.getItem("currentUser");
    if (currentUserEmail && location.pathname.includes("dashboard.html")) {
      const user = JSON.parse(localStorage.getItem(currentUserEmail));
      const userBooks = books.filter((book) =>
        user.categories.includes(book.category)
      );
  
      const bookList = document.getElementById("bookList");
      userBooks.forEach((book) => {
        const bookCard = document.createElement("div");
        bookCard.className = "col-md-4 mb-3";
        bookCard.innerHTML = `
                  <div class="card">
                      <div class="card-body">
                          <h5 class="card-title">${book.title}</h5>
                          <p class="card-text">${book.category}</p>
                      </div>
                  </div>
              `;
        bookList.appendChild(bookCard);
      });
    }
  };
  