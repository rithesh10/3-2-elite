const WebSocket = require("ws");
const mongoose = require("mongoose");
const AutoIncrement = require("mongoose-sequence")(mongoose);

mongoose.connect("mongodb://127.0.0.1:27017/employeeDB", { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => console.log("Connected to MongoDB"))
    .catch(err => console.error("MongoDB connection error:", err));

const employeeSchema = new mongoose.Schema({
    id: { type: Number, unique: true },
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

employeeSchema.plugin(AutoIncrement, { inc_field: "id" });
const Employee = mongoose.model("Employee", employeeSchema);

const wss = new WebSocket.Server({ port: 8080 }, () => console.log("WebSocket server running on port 8080"));

wss.on("connection", (ws) => {
    console.log("New client connected");
    ws.send("Connected");

    ws.on("message", async (message) => {
        console.log("Received:", message.toString());
        const args = message.toString().trim().split(" ");
        const command = args[0].toUpperCase();

        try {
            if (command === "INSERT" && args.length === 6) {
                const [, name, salary, role, department, experience] = args;
                if (isNaN(salary) || isNaN(experience)) {
                    ws.send("Invalid salary or experience.");
                    return;
                }
                const employee = new Employee({ name, salary: Number(salary), role, department, experience: Number(experience) });
                await employee.save();
                ws.send("Employee inserted successfully.");
            } 
            else if (command === "RETRIEVE") {
                const employees = await Employee.find();
                if (employees.length === 0) {
                    ws.send("No employees found.");
                } else {
                    employees.forEach((emp) => {
                        ws.send(`ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
                    });
                }
            } 
            else if (command === "RETRIEVE_BY_DEPT" && args.length === 2) {
                const department = args[1];
                const employees = await Employee.find({ department });
                if (employees.length === 0) {
                    ws.send("No employees found in this department.");
                } else {
                    employees.forEach((emp) => {
                        ws.send(`ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`);
                    });
                }
            } 
            else {
                ws.send("Invalid command.");
            }
        } catch (error) {
            ws.send("Error processing request.");
            console.error(error);
        }
    });

    ws.on("close", () => console.log("Client disconnected"));
});
