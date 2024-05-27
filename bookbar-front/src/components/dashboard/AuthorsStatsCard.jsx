import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserTie } from '@fortawesome/free-solid-svg-icons';
import ApiService from '../../api/ApiService';

const AuthorsStatsCard = () => {
    const [totalAuthors, setTotalAuthors] = useState(0);

    useEffect(() => {
        const fetchTotalAuthors = async () => {
            try {
                const response = await ApiService.getAuthors();
                setTotalAuthors(response.data.length);
            } catch (error) {
                console.error('Failed to fetch authors', error);
            }
        };

        fetchTotalAuthors();
    }, []);

    return (
        <Card style={{ width: '300px', height: '100px' }}>
            <Card.Body className="d-flex justify-content-between align-items-center">
                <FontAwesomeIcon icon={faUserTie} style={{ fontSize: '24px', marginRight:'15px' }} />
                <div style={{ flex: 1, textAlign: 'left' }}>Total Number of Authors: {totalAuthors}</div>
            </Card.Body>
        </Card>
    );
};

export default AuthorsStatsCard;