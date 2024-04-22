import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import Navbar from "./components/Navbar";
import CallToAction from './components/CallToAction';
import Carousel from './components/Carousel';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./pages/Register.jsx";
import Login from "./pages/Login.jsx";
import Footer from "./components/Footer.jsx";
import Home from "./pages/Home.jsx";

const App = () => {
    return (
        <>
            <Router>
                <Navbar />
                <Routes>
                    <Route path={"/"} element={<Home />} />
                    <Route path="/signup" element={<Register />} />
                    <Route path="/login" element={<Login />} />
                </Routes>
            </Router>
            {/*<CallToAction />*/}
            {/*<Carousel />*/}
            <Footer />
        </>
    )
}

export default App;
