import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const ErrorBoundary = ({ children }) => {
    const [hasError, setHasError] = useState(false);
    const [errorStatus, setErrorStatus] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const errorListener = (error) => {
            console.error("Error caught by ErrorBoundary:", error);

            if (error.response && error.response.status) {
                setErrorStatus(error.response.status);
            } else {
                setErrorStatus(500); // Default to 500 for unhandled errors
            }

            setHasError(true);
        };

        // Assuming an event emitter or some error reporting mechanism is used
        // You might have to customize this part based on how errors are handled in your app
        window.addEventListener("unhandledrejection", errorListener);

        return () => {
            window.removeEventListener("unhandledrejection", errorListener);
        };
    }, []);

    useEffect(() => {
        if (hasError) {
            if (errorStatus === 500) {
                navigate('/500');
            } else if (errorStatus === 403) {
                navigate('/403');
            } else if (errorStatus === 404) {
                navigate('/404');
            } else {
                navigate('/500'); // Default redirect for other error statuses
            }
        }
    }, [hasError, errorStatus, navigate]);

    return children;
};

export default ErrorBoundary;
