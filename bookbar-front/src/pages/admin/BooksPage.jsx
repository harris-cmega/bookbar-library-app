import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button, Form } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal';

const BooksPage = () => {
    const [books, setBooks] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newBook, setNewBook] = useState({
        title: '',
        language: '',
        publication_date: '',
        image: '',
        page_number: '',
        price: '',
        description: '',
        author_id: '',
        library_id: '',
        publisher_id: ''
    });
    const [editBook, setEditBook] = useState(null);
    const [deleteBook, setDeleteBook] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await ApiService.getBooks();
            setBooks(response.data);
        } catch (error) {
            console.error('Error fetching books:', error);
            setError('Error fetching books.');
        }
    };

    const handleAddBook = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createBook(newBook);
            setBooks(prevBooks => [...prevBooks, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add book:', error);
            setError('Failed to add book.');
        }
    };

    const handleEditClick = (book) => {
        setEditBook({
            id: book.id,
            title: book.title || '',
            language: book.language || '',
            publication_date: book.publication_date || '',
            image: book.image || '',
            page_number: book.page_number || '',
            price: book.price || '',
            description: book.description || '',
            author_id: book.author_id || '',
            library_id: book.library_id || '',
            publisher_id: book.publisher_id || ''
        });
        setShowModal(true);
    };

    const handleUpdateBook = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateBook(editBook.id, editBook);
            setBooks(prevBooks => prevBooks.map(book => book.id === editBook.id ? response.data : book));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update book:', error);
            setError('Failed to update book.');
        }
    };

    const handleDeleteBook = async () => {
        try {
            await ApiService.deleteBook(deleteBook.id);
            setBooks(prevBooks => prevBooks.filter(book => book.id !== deleteBook.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete book:', error);
            setError('Failed to delete book.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteBook) {
            await handleDeleteBook();
        } else if (editBook) {
            await handleUpdateBook(event);
        } else {
            await handleAddBook(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditBook(null);
        setDeleteBook(null);
        setNewBook({
            title: '',
            language: '',
            publication_date: '',
            image: '',
            page_number: '',
            price: '',
            description: '',
            author_id: '',
            library_id: '',
            publisher_id: ''
        });
    };

    return (
        <div>
            <h1 className="mb-4">Manage Books</h1>
            <Button variant="primary mt-3" onClick={() => setShowModal(true)}>+ Add Book</Button>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteBook ? "Confirm Delete" : (editBook ? "Edit Book" : "Add New Book")}
            >
                {deleteBook ? (
                    <p>Are you sure you want to delete {deleteBook.title}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            placeholder="Enter title"
                            value={editBook ? editBook.title : newBook.title}
                            onChange={e => editBook ? setEditBook({ ...editBook, title: e.target.value }) : setNewBook({ ...newBook, title: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter language"
                            value={editBook ? editBook.language : newBook.language}
                            onChange={e => editBook ? setEditBook({ ...editBook, language: e.target.value }) : setNewBook({ ...newBook, language: e.target.value })}
                        />
                        <Form.Control
                            type="date"
                            placeholder="Enter publication date"
                            value={editBook ? editBook.publication_date : newBook.publication_date}
                            onChange={e => editBook ? setEditBook({ ...editBook, publication_date: e.target.value }) : setNewBook({ ...newBook, publication_date: e.target.value })}
                        />
                        <Form.Control
                            type="number"
                            placeholder="Enter page number"
                            value={editBook ? editBook.page_number : newBook.page_number}
                            onChange={e => editBook ? setEditBook({ ...editBook, page_number: e.target.value }) : setNewBook({ ...newBook, page_number: e.target.value })}
                        />
                        <Form.Control
                            type="number"
                            placeholder="Enter price"
                            value={editBook ? editBook.price : newBook.price}
                            onChange={e => editBook ? setEditBook({ ...editBook, price: e.target.value }) : setNewBook({ ...newBook, price: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter description"
                            value={editBook ? editBook.description : newBook.description}
                            onChange={e => editBook ? setEditBook({ ...editBook, description: e.target.value }) : setNewBook({ ...newBook, description: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter author ID"
                            value={editBook ? editBook.author_id : newBook.author_id}
                            onChange={e => editBook ? setEditBook({ ...editBook, author_id: e.target.value }) : setNewBook({ ...newBook, author_id: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter library ID"
                            value={editBook ? editBook.library_id : newBook.library_id}
                            onChange={e => editBook ? setEditBook({ ...editBook, library_id: e.target.value }) : setNewBook({ ...newBook, library_id: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter publisher ID"
                            value={editBook ? editBook.publisher_id : newBook.publisher_id}
                            onChange={e => editBook ? setEditBook({ ...editBook, publisher_id: e.target.value }) : setNewBook({ ...newBook, publisher_id: e.target.value })}
                        />
                    </>
                )}
            </ReusableModal>
            <Table striped bordered hover responsive>
                <thead className="bg-primary text-white">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Language</th>
                    <th>Publication Date</th>
                    <th>Page Number</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>Library</th>
                    <th>Publisher</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book.id}>
                        <td>{book.id}</td>
                        <td>{book.title}</td>
                        <td>{book.language}</td>
                        <td>{book.publication_date}</td>
                        <td>{book.page_number}</td>
                        <td>{book.price}</td>
                        <td>{book.description}</td>
                        <td>{book.author_name}</td>
                        <td>{book.library_name}</td>
                        <td>{book.publisher_name}</td>
                        <td>
                            <Button variant="warning" className="me-2" onClick={() => handleEditClick(book)}>Edit</Button>
                            <Button variant="danger" onClick={() => {
                                setDeleteBook(book);
                                setShowModal(true);
                            }}>Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default BooksPage;
