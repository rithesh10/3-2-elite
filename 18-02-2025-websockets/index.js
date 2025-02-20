// <!--
// Employee Management WebSocket Application

// Objective:
// -----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js. 
// This system will allow clients to:
//     1. Insert Employee Records (INSERT <name> <salary>)
//     2. Retrieve Employee List (RETRIEVE)
//     3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
// Your goal is to implement and test a WebSocket-based server and client, 
// ensuring that all operations work correctly.

// Requirements:
// -------------
// 1. Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections.
// 		-> Process client messages and handle commands:
// 			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
// 			2. RETRIEVE → Returns all stored employees.
// 			3. Any other command should return "Invalid command."
// 		-> Maintain an in-memory array of employees (no database required).
// 		-> Log each received command on the console.
		
		
// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			Server Response
// ============================================================================================
// INSERT Alice 50000		"Employee inserted successfully."
// INSERT Bob 60000		"Employee inserted successfully."
// RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
//                         "ID: 2, Name: Bob, Salary: 60000"
// INVALID					"Invalid command."
// ============================================================================================

// Note: 
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080
// -->
// <config>
//     <url value=""></url>
// </config>
// const WebSocket = require("ws");

// const wss = new WebSocket.Server({ port: 8080 });

// wss.on("connection", (ws) => {
//     console.log("Client connected");

//     // Sending a welcome message when a client connects
//     ws.send("Welcome to the WebSocket server!");

//     // Listening for messages from the client
//     ws.on("message", (message) => {
//         console.log(`Received: ${message}`);
//         ws.send(`Server received: ${message}`); // Echoing back the message
//     });

//     // Handling client disconnection
//     ws.on("close", () => {
//         console.log("Client disconnected");
//     });
// });

// console.log("WebSocket server running on ws://localhost:8080");
const WebSocket=require('ws');
const wss=new WebSocket.Server({port:8080});
console.log(`webserver running at ws://localhost:${8080}`)
const employee=[];
let employeeID=1;
wss.on('connection',(ws)=>{
    console.log("client connected");
    ws.send("Welcome to the page ");
    ws.on('message',(message)=>{
        const parts=message.toString().trim().split(' ');
        const first=parts[0].toUpperCase();
        if(first==="INSERT" && parts.length==3)
        {
            let name=parts[1];
            let salary=parseFloat(parts[2]);
            if(!isNaN(salary) && salary>0)
            {

                employee.push({id:employeeID++,name,salary});
                ws.send("Employee inserted successfully.")
            }
            else{
                ws.send("Invalid data")
            }
        }
        else if(first=="RETRIEVE"){
            employee.forEach(element => {
                ws.send("ID: "+element.id+","+"Name: "+element.name+","+"Salary: "+element.salary)
            });


        }
        else{
            ws.send("Invalid command.")

        }
        // console.log("recieved message from client"+message);
        // ws.send("Server recieved :"+message);
    })
    ws.on('close',()=>{
        console.log("Client disconnected");
    })
})