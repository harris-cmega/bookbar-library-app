import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button } from 'react-bootstrap';

const PublishersPage = () => {
    const [publishers, setPublishers] = useState([]);

    useEffect(() => {
        ApiService.getPublishers().then(response => {
            setPublishers(response.data);
        }).catch(error => {
            console.error('Error fetching publishers:', error);
        });
    }, []);

    return (
        <div>
            <h1>Manage Publishers</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {publishers.map(publisher => (
                    <tr key={publisher.id}>
                        <td>{publisher.id}</td>
                        <td>{publisher.name}</td>
                        <td>{publisher.address}</td>
                        <td>
                            <Button variant="warning">Edit</Button>{' '}
                            <Button variant="danger">Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default PublishersPage;
