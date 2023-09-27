const express = require("express");
const router = express.Router();
import { authUser } from "../controller/authController.mjs";

//get user auth
router.post("/user", authUser);

export { router as authRoutes};
