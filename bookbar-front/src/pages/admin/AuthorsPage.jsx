import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button } from 'react-bootstrap';

const AuthorsPage = () => {
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        ApiService.getAuthors().then(response => {
            setAuthors(response.data);
        }).catch(error => {
            console.error('Error fetching authors:', error);
        });
    }, []);

    return (
        <div>
            <h1>Manage Authors</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Biography</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {authors.map(author => (
                    <tr key={author.id}>
                        <td>{author.id}</td>
                        <td>{author.name}</td>
                        <td>{author.biography}</td>
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

export default AuthorsPage;
