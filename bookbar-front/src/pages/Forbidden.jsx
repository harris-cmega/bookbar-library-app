import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Forbidden = () => {
    return (
        <div className="container text-center mt-5">
            <h1 className="display-1">403</h1>
            <h2 className="display-4">Forbidden</h2>
            <p className="lead">You don't have permission to access this page. It seems like a chapter you can't read yet!</p>
            <a className="btn btn-primary" href="/">Go Back Home</a>
        </div>
    );
};

export default Forbidden;
