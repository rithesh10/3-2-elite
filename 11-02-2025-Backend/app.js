const express = require("express");
const app = express();

app.use(express.json()); 

let orders = [];
let nextId = 1; // Start order IDs from 1

// CREATE - Add a new order
app.post("/orders", (req, res) => {
    const { customerName, totalPrice } = req.body;

    // Validation
    if (!customerName || typeof totalPrice !== "number" || totalPrice <= 0) {
        return res.status(400).json({ error: "Invalid input data" });
    }

    const newOrder = {
        id: nextId++,
        customerName,
        totalPrice
    };

    orders.push(newOrder);
    res.status(201).json(newOrder);
});

// READ - Get all orders
app.get("/orders", (req, res) => {
    res.status(200).json(orders);
});

// READ - Get an order by ID
app.get("/orders/:id", (req, res) => {
    const orderId = parseInt(req.params.id);
    const order = orders.find(o => o.id === orderId);

    if (!order) {
        return res.status(404).send();
    }

    res.status(200).json(order);
});

// UPDATE - Modify an order by ID
app.put("/orders/:id", (req, res) => {
    const orderId = parseInt(req.params.id);
    const { customerName, totalPrice } = req.body;

    const orderIndex = orders.findIndex(o => o.id === orderId);
    if (orderIndex === -1) {
        return res.status(404).send();
    }

    // Validation
    if (!customerName || typeof totalPrice !== "number" || totalPrice <= 0) {
        return res.status(400).json({ error: "Invalid input data" });
    }

    orders[orderIndex] = { id: orderId, customerName, totalPrice };
    res.status(200).json(orders[orderIndex]);
});

// DELETE - Remove an order by ID
app.delete("/orders/:id", (req, res) => {
    const orderId = parseInt(req.params.id);
    const orderIndex = orders.findIndex(o => o.id === orderId);

    if (orderIndex === -1) {
        return res.status(404).send();
    }

    orders.splice(orderIndex, 1);
    res.status(200).send();
});

// Start server
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
