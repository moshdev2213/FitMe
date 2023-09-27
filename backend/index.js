const express = require("express");
const cors = require("cors");
const dotenv = require("dotenv");
const app = express();


const corsConfig = require("./config/corsConfig");
dotenv.config()

const PORT = process.env.PORT || 3500;

// middleware
app.use(express.json());
app.use((req, res, next) => {
  console.log(req.path, req.method);
  next();
});
app.use(cors(corsConfig));

//import routes
import { authRoutes }from "./routes/authRouter.mjs"
//use routes
app.use("api/auth",authRoutes)


app.listen(PORT, () => {
    console.log(`Server is listening on http://localhost:${PORT}`);
});
  
module.exports = app