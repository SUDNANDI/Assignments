const express = require("express");
const path = require("path");
const session = require("express-session");
const app = express();
const PORT = process.env.PORT || 3000;
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, "public")));
app.use(
session({
secret: "secret-key",
resave: false,
saveUninitialized: true,
})
);
let products = require("./data");
console.log(products);
const adminCredentials = { username: "admin", password: "admin123" };
const userCredentials = { username: "user", password: "user123" };
app.get("/", (req, res) => {
res.sendFile(path.join(__dirname, "public/views/index.html"));
});
app.get("/login", (req, res) => {
res.sendFile(path.join(__dirname, "public/views/login.html"));
});
app.post("/login", (req, res) => {
const { username, password } = req.body;
if (
username === adminCredentials.username &&
password === adminCredentials.password
) {
req.session.role = "admin";
res.redirect("/admin-dashboard");
} else if (
username === userCredentials.username &&
password === userCredentials.password
) {
req.session.role = "user";
res.redirect("/user-dashboard");
} else {
res.send("Invalid credentials. Please try again.");
}
});
app.get("/logout", (req, res) => {
req.session.destroy((err) => {
if (err) return res.send("Error logging out.");
res.redirect("/login");
});
});
app.get("/admin-dashboard", (req, res) => {
if (req.session.role === "admin") {
res.sendFile(path.join(__dirname, "public/views/admin-dashboard.html"));
} else {
res.redirect("/login");
}
});
app.post("/add-product", (req, res) => {
if (req.session.role === "admin") {
const { productName, productId, price, category, mfgDate, expDate } =
req.body;
products.push({
productName,
productId,
price,
category,
mfgDate,
expDate,
});
res.redirect("/product-list");
} else {
res.redirect("/login");
}
});
app.get("/product-list", (req, res) => {
if (req.session.role === "admin") {
res.json(products);
} else {
res.redirect("/login");
}
});
app.get("/user-dashboard", (req, res) => {
if (req.session.role === "user") {
res.sendFile(path.join(__dirname, "public/views/user-dashboard.html"));
} else {
res.redirect("/login");
}
});
app.get("/search-products", (req, res) => {
const { query, category } = req.query;
if (!query && !category) {
return res.json([]);
}
const filteredProducts = products.filter((product) => {
const matchesQuery = query
? product.productName.toLowerCase().includes(query.toLowerCase())
: true;
const matchesCategory = category
? product.category.toLowerCase().includes(category.toLowerCase())
: true;
return matchesQuery && matchesCategory;
});
res.json(filteredProducts);
});
app.listen(PORT, () => {
console.log(`Server running on http://localhost:${PORT}`);
});
