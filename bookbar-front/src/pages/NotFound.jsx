import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const NotFound = () => {
    return (
        <div className="container text-center mt-5">
            <h1 className="display-1">404</h1>
            <h2 className="display-4">Page Not Found</h2>
            <p className="lead">It looks like you've turned to a non-existing page. Perhaps a new book adventure awaits you elsewhere!</p>
            <a className="btn btn-primary" href="/">Go Back Home</a>
        </div>
    );
};

export default NotFound;
