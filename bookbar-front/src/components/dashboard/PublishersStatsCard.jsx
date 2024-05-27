import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAddressBook } from '@fortawesome/free-solid-svg-icons'; // Icon for publishers
import ApiService from '../../api/ApiService';

const PublishersStatsCard = () => {
    const [totalPublishers, setTotalPublishers] = useState(0);

    useEffect(() => {
        const fetchTotalPublishers = async () => {
            try {
                const response = await ApiService.getPublishers(); // Adjusted to fetch publishers
                setTotalPublishers(response.data.length);
            } catch (error) {
                console.error('Failed to fetch publishers', error);
            }
        };

        fetchTotalPublishers();
    }, []);

    return (
        <Card style={{ width: '300px', height: '100px' }}>
            <Card.Body className="d-flex justify-content-between align-items-center">
                <FontAwesomeIcon icon={faAddressBook} style={{ fontSize: '24px', marginRight:'15px' }} />
                <div style={{ flex: 1, textAlign: 'left' }}>Total Number of Publishers: {totalPublishers}</div>
            </Card.Body>
        </Card>
    );
};

export default PublishersStatsCard;