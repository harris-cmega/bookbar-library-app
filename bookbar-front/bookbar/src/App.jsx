import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import Navbar from "./components/Navbar";
import CallToAction from './components/CallToAction';
import Carousel from './components/Carousel';

const App = () => {
  return (
    <>
      <Navbar />
      <CallToAction />
      <Carousel />
    </>
  )
}

export default App