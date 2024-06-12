import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const BookFilesPage = () => {
    const [bookFiles, setBookFiles] = useState([]);
    const [books, setBooks] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newBookFile, setNewBookFile] = useState({ id: '', filename: '', size: '', format: '', epubFile: '', bookId: '' });
    const [editBookFile, setEditBookFile] = useState(null);
    const [deleteBookFile, setDeleteBookFile] = useState(null);
    const [error, setError] = useState('');
    const [file, setFile] = useState(null);

    useEffect(() => {
        fetchBookFiles();
        fetchBooks();
    }, []);

    const fetchBookFiles = async () => {
        try {
            const response = await ApiService.getBookFiles();
            setBookFiles(response.data || []);
        } catch (error) {
            console.error('Error fetching book files:', error);
            setError('Error fetching book files.');
        }
    };

    const fetchBooks = async () => {
        try {
            const response = await ApiService.getBooks();
            setBooks(response.data.content || []);
        } catch (error) {
            console.error('Error fetching books:', error);
            setError('Error fetching books.');
        }
    };

    const handleAddBookFile = async (event) => {
        event.preventDefault();
        try {
            let filePath = '';
            if (file) {
                const uploadResponse = await ApiService.uploadBookFileFile(file);
                filePath = uploadResponse.data;
            }

            const newBookFileWithFile = { ...newBookFile, epubFile: filePath };

            const response = await ApiService.createBookFile(newBookFileWithFile);
            setBookFiles(prevBookFiles => [...prevBookFiles, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add book file:', error.response || error);
            setError('Failed to add book file. ' + (error.response?.data?.message || ''));
        }
    };

    const handleEditClick = (bookFile) => {
        setEditBookFile({
            id: bookFile.id,
            filename: bookFile.filename || '',
            size: bookFile.size || '',
            format: bookFile.format || '',
            epubFile: bookFile.epubFile || '',
            bookId: bookFile.bookId || ''
        });
        setShowModal(true);
    };

    const handleUpdateBookFile = async (event) => {
        event.preventDefault();
        try {
            const updatedBookFile = { ...editBookFile };
            const response = await ApiService.updateBookFile(editBookFile.id, updatedBookFile);
            setBookFiles(prevBookFiles => prevBookFiles.map(bookFile => bookFile.id === editBookFile.id ? response.data : bookFile));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update book file:', error.response || error);
            setError('Failed to update book file. ' + (error.response?.data?.message || ''));
        }
    };

    const handleDeleteBookFile = async () => {
        try {
            await ApiService.deleteBookFile(deleteBookFile.id);
            setBookFiles(prevBookFiles => prevBookFiles.filter(bookFile => bookFile.id !== deleteBookFile.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete book file:', error);
            setError('Failed to delete book file.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteBookFile) {
            await handleDeleteBookFile();
        } else if (editBookFile) {
            await handleUpdateBookFile(event);
        } else {
            await handleAddBookFile(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditBookFile(null);
        setDeleteBookFile(null);
        setNewBookFile({ filename: '', size: '', format: '', epubFile: '', bookId: '' });
        setFile(null);
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setFile(file);
        setNewBookFile({ ...newBookFile, size: file.size, format: file.type });
    };

    const handleEditFileChange = (e) => {
        const file = e.target.files[0];
        setFile(file);
        const reader = new FileReader();
        reader.onloadend = () => {
            const base64String = reader.result.replace("data:", "").replace(/^.+,/, "");
            setEditBookFile({ ...editBookFile, epubFile: base64String, size: file.size, format: file.type });
        };
        reader.readAsDataURL(file);
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Book Files</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>+ Add Book File</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteBookFile ? "Confirm Delete" : (editBookFile ? "Edit Book File" : "Add New Book File")}
            >
                {deleteBookFile ? (
                    <p>Are you sure you want to delete {deleteBookFile.filename}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter filename"
                            value={editBookFile ? editBookFile.filename : newBookFile.filename}
                            onChange={e => editBookFile ? setEditBookFile({ ...editBookFile, filename: e.target.value }) : setNewBookFile({ ...newBookFile, filename: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter size"
                            value={editBookFile ? editBookFile.size : newBookFile.size}
                            onChange={e => editBookFile ? setEditBookFile({ ...editBookFile, size: e.target.value }) : setNewBookFile({ ...newBookFile, size: e.target.value })}
                            disabled
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter format (must be .EPUB)"
                            value={editBookFile ? editBookFile.format : newBookFile.format}
                            onChange={e => editBookFile ? setEditBookFile({ ...editBookFile, format: e.target.value }) : setNewBookFile({ ...newBookFile, format: e.target.value })}
                            disabled
                        />
                        <Form.Control
                            type="file"
                            className="mb-2"
                            onChange={editBookFile ? handleEditFileChange : handleFileChange}
                        />
                        <Form.Select
                            className="mb-2"
                            value={editBookFile ? editBookFile.bookId : newBookFile.bookId}
                            onChange={e =>
                                editBookFile
                                    ? setEditBookFile({ ...editBookFile, bookId: e.target.value })
                                    : setNewBookFile({ ...newBookFile, bookId: e.target.value })
                            }
                        >
                            <option value="">Select Book</option>
                            {books.length > 0 ? books.map(book => (
                                <option key={book.id} value={book.id}>{book.title}</option>
                            )) : <option disabled>No books available</option>}
                        </Form.Select>
                    </>
                )}
            </ReusableModal>
            <Table hover className="mt-2 text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Filename</th>
                        <th>Size</th>
                        <th>Format</th>
                        <th>Book ID</th>
                        <th>File</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {bookFiles.map(bookFile => (
                        <tr key={bookFile.id}>
                            <td>{bookFile.id}</td>
                            <td>{bookFile.filename}</td>
                            <td>{bookFile.size}</td>
                            <td>{bookFile.format}</td>
                            <td>{bookFile.bookId}</td>
                            <td>{bookFile.epubFile ? 'Yes' : 'No'}</td>
                            <td>
                                <Button variant="outline-secondary btn-sm text-dark me-2" onClick={() => handleEditClick(bookFile)}>Edit</Button>
                                <Button variant="danger btn-sm" onClick={() => {
                                    setDeleteBookFile(bookFile);
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

export default BookFilesPage;
