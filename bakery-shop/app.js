// Defining a product prototype
function Product(name, price, weight, image) {
    this.name = name;
    this.price = price;
    this.weight = weight;
    this.image = image;
  }
  
  Product.prototype.getProductDetails = function() {
    return `${this.name} - $${this.price} - ${this.weight}g`;
  };
  
  Product.prototype.render = function() {
    const productElement = document.createElement("div");
    productElement.classList.add("product-item");
  
    productElement.innerHTML = `
      <img src="${this.image}" alt="${this.name}">
      <h3>${this.name}</h3>
      <p>Price: $${this.price}</p>
      <p>Weight: ${this.weight}g</p>
      <button class="add-to-cart-btn">Add to Cart</button>
    `;
  
    return productElement;
  };
  
  // Sample products
  const products = [
    new Product("Chocolate Cake", 15.99, 500, "https://www.mybakingaddiction.com/wp-content/uploads/2011/10/lr-0968.jpg"),
    new Product("Apple Pastry", 5.49, 150, "https://midwestfoodieblog.com/wp-content/uploads/2021/09/FINAL-puff-pastry-apple-tart-1-3-1200x1800.jpg"),
    new Product("Almond Croissant", 3.99, 120, "https://simplyhomecooked.com/wp-content/uploads/2016/02/almond-croissant-5.jpg"),
    new Product("Vanilla Cake", 12.99, 450, "https://sallysbakingaddiction.com/wp-content/uploads/2019/01/vanilla-cake-3.jpg"),
  ];
  
  // Cart
  let cart = [];
  
  // Function to render products
  function renderProducts() {
    const productList = document.getElementById("product-list");
    products.forEach(product => {
      const productElement = product.render();
      productList.appendChild(productElement);
      
      // Add event listener to the "Add to Cart" button
      const addToCartButton = productElement.querySelector(".add-to-cart-btn");
      addToCartButton.addEventListener("click", () => addToCart(product));
    });
  }
  
  // Function to add product to the cart
  function addToCart(product) {
    cart.push(product);
    updateCart();
  }
  
  // Function to update cart
  function updateCart() {
    const cartCount = document.getElementById("cart-count");
    const cartDetails = document.getElementById("cart-details");
    const checkoutButton = document.getElementById("checkout-btn");
  
    // Update the cart count
    cartCount.textContent = cart.length;
  
    // Display cart details
    if (cart.length > 0) {
      cartDetails.innerHTML = cart.map(item => `<p>${item.getProductDetails()}</p>`).join("");
      checkoutButton.disabled = false;
    } else {
      cartDetails.innerHTML = "<p>Your cart is empty.</p>";
      checkoutButton.disabled = true;
    }
  }
  
  // Event listener for checkout
  document.getElementById("checkout-btn").addEventListener("click", () => {
    alert("Proceeding to checkout...");
    cart = [];
    updateCart();
  });
  
  // Initialize the app
  window.onload = function() {
    renderProducts();
  };
  
