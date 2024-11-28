const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const app = express();

// Middleware to parse incoming request bodies
app.use(bodyParser.urlencoded({ extended: true }));

// Middleware to serve static files from the "public" folder
app.use(express.static(path.join(__dirname, 'public')));

// Set the view engine to EJS
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Sample product data (in-memory database)
let products = [
    { name: 'Apple iPhone 15 Pro', description: 'The latest iPhone with A17 Bionic chip.' },
    { name: 'Sony WH-1000XM5', description: 'Premium noise-cancelling wireless headphones.' },
    { name: 'Dyson V15 Detect', description: 'Powerful cordless vacuum cleaner.' }
];

// Route to show the product list
app.get('/', (req, res) => {
    res.render('index', { products });
});

// Route to show the "add-product" form
app.get('/add-product', (req, res) => {
    res.render('add-product');
});

// Route to handle the "add-product" form submission
app.post('/add-product', (req, res) => {
    const { name, description } = req.body;  // Getting data from the form
    if (name && description) {
        // Add new product to the products array
        products.push({ name, description });
    }
    // Redirect back to the product list
    res.redirect('/');
});

// Start the server
const port = 3000;
app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});