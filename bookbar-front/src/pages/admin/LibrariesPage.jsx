import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button } from 'react-bootstrap';

const LibrariesPage = () => {
    const [libraries, setLibraries] = useState([]);

    useEffect(() => {
        ApiService.getLibraries().then(response => {
            setLibraries(response.data);
        }).catch(error => {
            console.error('Error fetching libraries:', error);
        });
    }, []);

    return (
        <div>
            <h1>Manage Libraries</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {libraries.map(library => (
                    <tr key={library.id}>
                        <td>{library.id}</td>
                        <td>{library.name}</td>
                        <td>{library.location}</td>
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

export default LibrariesPage;
