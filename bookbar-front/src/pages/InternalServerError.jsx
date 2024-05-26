import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const InternalServerError = () => {
    return (
        <div className="container text-center mt-5">
            <h1 className="display-1">500</h1>
            <h2 className="display-4">Internal Server Error</h2>
            <p className="lead">Something went wrong on our end. The book of errors has opened, and we're working to close it!</p>
            <a className="btn btn-primary" href="/">Go Back Home</a>
        </div>
    );
};

export default InternalServerError;
