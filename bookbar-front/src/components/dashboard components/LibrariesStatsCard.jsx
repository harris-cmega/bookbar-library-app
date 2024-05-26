import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBuilding } from '@fortawesome/free-solid-svg-icons';
import ApiService from '../../api/ApiService';

const LibrariesStatsCard = () => {
    const [totalLibraries, setTotalLibraries] = useState(0);

    useEffect(() => {
        const fetchTotalLibraries = async () => {
            try {
                const response = await ApiService.getLibraries();
                setTotalLibraries(response.data.length);
            } catch (error) {
                console.error('Failed to fetch libraries', error);
            }
        };

        fetchTotalLibraries();
    }, []);

    return (
        <Card style={{ width: '300px', height: '100px' }}>
            <Card.Body className="d-flex justify-content-between align-items-center">
                <FontAwesomeIcon icon={faBuilding} style={{ fontSize: '24px', marginRight:'15px' }} />
                <div style={{ flex: 1, textAlign: 'left' }}>Total Number of Libraries: {totalLibraries}</div>
            </Card.Body>
        </Card>
    );
};

export default LibrariesStatsCard;