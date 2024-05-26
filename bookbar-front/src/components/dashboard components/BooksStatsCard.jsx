import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBook } from '@fortawesome/free-solid-svg-icons';
import ApiService from '../../api/ApiService';

const BooksStatsCard = () => {
    const [totalBooks, setTotalBooks] = useState(0);

    useEffect(() => {
        const fetchTotalBooks = async () => {
            try {
                const response = await ApiService.getBooks();
                setTotalBooks(response.data.length);
            } catch (error) {
                console.error('Failed to fetch books', error);
            }
        };

        fetchTotalBooks();
    }, []);

    return (
        <Card style={{ width: '300px', height: '100px' }}>
            <Card.Body className="d-flex justify-content-between align-items-center">
                <FontAwesomeIcon icon={faBook} style={{ fontSize: '24px', marginRight:'15px' }} />
                <div style={{ flex: 1, textAlign: 'left' }}>Total Number of Books: {totalBooks}</div>
            </Card.Body>
        </Card>
    );
};

export default BooksStatsCard;