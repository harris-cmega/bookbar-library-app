import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button, Form } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal';
import { ChevronDownIcon } from '@heroicons/react/24/solid';

const BookCategoriesPage = () => {
    const [bookCategories, setBookCategories] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newBookCategory, setNewBookCategory] = useState({
        book_id: '',
        category_id: ''
    });
    const [editBookCategory, setEditBookCategory] = useState(null);
    const [deleteBookCategory, setDeleteBookCategory] = useState(null);
    const [error, setError] = useState('');
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [books, setBooks] = useState([]);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetchBookCategories(page);
        fetchBooks();
        fetchCategories();
    }, [page]);

    const fetchBookCategories = async (page) => {
        try {
            const response = await ApiService.getBookCategories({ page });
            setBookCategories(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.error('Error fetching book categories:', error);
            setError('Error fetching book categories.');
        }
    };

    const fetchBooks = async () => {
        try {
            const response = await ApiService.getBooks(0, 100); // Fetch a large number of books
            setBooks(response.data.content);
        } catch (error) {
            console.error('Error fetching books:', error);
            setError('Error fetching books.');
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await ApiService.getCategories();
            setCategories(response.data);
        } catch (error) {
            console.error('Error fetching categories:', error);
            setError('Error fetching categories.');
        }
    };

    const handleAddBookCategory = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createBookCategory(newBookCategory);
            setBookCategories(prevBookCategories => [...prevBookCategories, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add book category:', error);
            setError('Failed to add book category.');
        }
    };

    const handleEditClick = (bookCategory) => {
        setEditBookCategory({
            id: bookCategory.id,
            book_id: bookCategory.book_id,
            category_id: bookCategory.category_id
        });
        setShowModal(true);
    };

    const handleUpdateBookCategory = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateBookCategory(editBookCategory.id, editBookCategory);
            setBookCategories(prevBookCategories => prevBookCategories.map(bc => bc.id === editBookCategory.id ? response.data : bc));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update book category:', error);
            setError('Failed to update book category.');
        }
    };

    const handleDeleteBookCategory = async () => {
        try {
            await ApiService.deleteBookCategory(deleteBookCategory.id);
            setBookCategories(prevBookCategories => prevBookCategories.filter(bc => bc.id !== deleteBookCategory.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete book category:', error);
            setError('Failed to delete book category.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteBookCategory) {
            await handleDeleteBookCategory();
        } else if (editBookCategory) {
            await handleUpdateBookCategory(event);
        } else {
            await handleAddBookCategory(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditBookCategory(null);
        setDeleteBookCategory(null);
        setNewBookCategory({
            book_id: '',
            category_id: ''
        });
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Book Categories</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>Add Book Category</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteBookCategory ? "Confirm Delete" : (editBookCategory ? "Edit Book Category" : "Add New Book Category")}
            >
                {deleteBookCategory ? (
                    <p>Are you sure you want to delete this book category?</p>
                ) : (
                    <>
                        <Form.Control
                            as="select"
                            value={editBookCategory ? editBookCategory.book_id : newBookCategory.book_id}
                            onChange={e => editBookCategory ? setEditBookCategory({
                                ...editBookCategory,
                                book_id: e.target.value
                            }) : setNewBookCategory({...newBookCategory, book_id: e.target.value})}
                            className="mb-2"
                        >
                            <option value="">Select Book</option>
                            {books.map(book => (
                                <option key={book.id} value={book.id}>{book.title}</option>
                            ))}
                        </Form.Control>
                        <Form.Control
                            as="select"
                            value={editBookCategory ? editBookCategory.category_id : newBookCategory.category_id}
                            onChange={e => editBookCategory ? setEditBookCategory({
                                ...editBookCategory,
                                category_id: e.target.value
                            }) : setNewBookCategory({...newBookCategory, category_id: e.target.value})}
                            className="mb-2"
                        >
                            <option value="">Select Category</option>
                            {categories.map(category => (
                                <option key={category.id} value={category.id}>{category.name}</option>
                            ))}
                        </Form.Control>
                    </>
                )}
            </ReusableModal>
            <Table hover className="text-center mt-2">
                <thead className="bg-primary text-white">
                <tr>
                    <th>ID</th>
                    <th>Book</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {bookCategories.map(bookCategory => (
                    <tr key={bookCategory.id}>
                        <td>{bookCategory.id}</td>
                        <td>{bookCategory.book_title}</td>
                        <td>{bookCategory.category_name}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm" className="me-2" onClick={() => handleEditClick(bookCategory)}>Edit</Button>
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeleteBookCategory(bookCategory);
                                setShowModal(true);
                            }}>Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {bookCategories.length > 10 && (
                <div className="pagination">
                    {[...Array(totalPages).keys()].map((number) => (
                        <Button
                            key={number}
                            className={`btn ${number === page ? 'btn-primary' : 'btn-secondary'}`}
                            onClick={() => setPage(number)}
                        >
                            {number + 1}
                        </Button>
                    ))}
                </div>
            )}
        </div>
    );
};

export default BookCategoriesPage;
