import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button } from 'react-bootstrap';

const BooksPage = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        ApiService.getBooks().then(response => {
            setBooks(response.data);
        }).catch(error => {
            console.error('Error fetching books:', error);
        });
    }, []);

    return (
        <div>
            <h1>Manage Books</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book.id}>
                        <td>{book.id}</td>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td>{book.publisher}</td>
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

export default BooksPage;
