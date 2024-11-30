const express = require('express');
const path = require('path');
const session = require('express-session');
 
const app = express();
const PORT = process.env.PORT || 3000;
 
// Middleware
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));
app.use(
  session({
    secret: 'secret-key',
    resave: false,
    saveUninitialized: true,
  })
);
 
// Simulated database
let products = []; // Stores product details
const adminCredentials = { username: 'admin', password: 'admin123' };
const userCredentials = { username: 'user', password: 'user123' };
 
// Routes
// Home Page
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/views/index.html'));
});
 
// Login Page
app.get('/login', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/views/login.html'));
});
 
app.post('/login', (req, res) => {
  const { username, password } = req.body;
 
  if (username === adminCredentials.username && password === adminCredentials.password) {
    req.session.role = 'admin';
    res.redirect('/admin-dashboard');
  } else if (username === userCredentials.username && password === userCredentials.password) {
    req.session.role = 'user';
    res.redirect('/user-dashboard');
  } else {
    res.send('Invalid credentials. Please try again.');
  }
});
 
// Logout Route
app.get('/logout', (req, res) => {
  req.session.destroy((err) => {
    if (err) return res.send('Error logging out.');
    res.redirect('/login');
  });
});
 
// Admin Dashboard
app.get('/admin-dashboard', (req, res) => {
  if (req.session.role === 'admin') {
    res.sendFile(path.join(__dirname, 'public/views/admin-dashboard.html'));
  } else {
    res.redirect('/login');
  }
});
 
// Add a New Product
app.post('/add-product', (req, res) => {
  if (req.session.role === 'admin') {
    const { productName, productId, price, category, mfgDate, expDate } = req.body;
    products.push({ productName, productId, price, category, mfgDate, expDate });
    res.redirect('/product-list');
  } else {
    res.redirect('/login');
  }
});
 
// View Product List
app.get('/product-list', (req, res) => {
  if (req.session.role === 'admin') {
    res.json(products);
  } else {
    res.redirect('/login');
  }
});
 
// User Dashboard
app.get('/user-dashboard', (req, res) => {
  if (req.session.role === 'user') {
    res.sendFile(path.join(__dirname, 'public/views/user-dashboard.html'));
  } else {
    res.redirect('/login');
  }
});
 
// Search Products
app.get('/search-products', (req, res) => {
    const { query, category } = req.query;
    
    // Filter products based on name and category
    const filteredProducts = products.filter(product => {
      return (
        product.productName.toLowerCase().includes(query.toLowerCase()) ||
        product.category.toLowerCase().includes(category.toLowerCase())
      );
    });
   
    // Return filtered products as a JSON response
    res.json(filteredProducts);
  });
 
// Start the server
app.listen(PORT, () => {
console.log(`Server running on http://localhost:${PORT}`);
});
