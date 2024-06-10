import React from 'react';
import { Link } from 'react-router-dom';
import '../index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleCheck } from '@fortawesome/free-solid-svg-icons'; // Import specific icon

const Success = () => {
    return (
        <div className="container text-center mt-5 success-page">
            <FontAwesomeIcon icon={faCircleCheck} style={{ color: "#eb3188", fontSize: "4rem" }} />
            <h1 className="display-4">Your Order is Confirmed!</h1>
            <p className="lead">Your checkout was successful and your order will be processed shortly. Happy Reading!</p>
            <Link to="/" className="btn successBtn">Return to Home</Link>
        </div>
    );
};

export default Success;