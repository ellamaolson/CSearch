// App.js used for starting and initializing app

import express from "express";
import bodyParser from "body-parser";

// initialize the express app
const app = express();

// use dependencies here
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// use APIs here (controllers)
app.get('/', (req, res) => {
    res.status(200).json({
        status: 'Server is running!'
    });
});

// start server on port 8080
app.listen(8080, () => {
    console.log('Server running on port 8080.');
});