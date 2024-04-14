import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import Navbar from "./components/Navbar";
import CallToAction from './components/CallToAction';
import Carousel from './components/Carousel';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./pages/Register.jsx";
import Login from "./pages/Login.jsx";

const App = () => {
    return (
        <>
            <Router>
                <Navbar />
                <Routes>
                    <Route path="/signup" element={<Register />} />
                    <Route path="/login " element={<Login />} />
                </Routes>
            </Router>
            <CallToAction />
            {/* Carousel is rendered outside Router, so it only appears on the home page */}
            <Carousel />
        </>
    )
}

export default App;
