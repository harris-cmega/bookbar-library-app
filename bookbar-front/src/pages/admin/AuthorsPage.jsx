import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const AuthorsPage = () => {
    const [authors, setAuthors] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newAuthor, setNewAuthor] = useState({ name: '', email: '', biography: '', nationality: '', birthDate: '', deathDate: '' });
    const [editAuthor, setEditAuthor] = useState(null);
    const [deleteAuthor, setDeleteAuthor] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchAuthors();
    }, []);

    const fetchAuthors = async () => {
        try {
            const response = await ApiService.getAuthors();
            setAuthors(response.data);
        } catch (error) {
            console.error('Error fetching authors:', error);
            setError('Error fetching authors.');
        }
    };

    const handleAddAuthor = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createAuthor(newAuthor);
            setAuthors(prevAuthors => [...prevAuthors, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add author:', error);
            setError('Failed to add author.');
        }
    };

    const handleEditClick = (author) => {
        setEditAuthor({
            id: author.id,
            name: author.name || '',
            email: author.email || '',
            biography: author.biography || '',
            nationality: author.nationality || '',
            birthDate: author.birthDate || '',
            deathDate: author.deathDate || ''
        });
        setShowModal(true);
    };

    const handleUpdateAuthor = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateAuthor(editAuthor.id, editAuthor);
            setAuthors(prevAuthors => prevAuthors.map(author => author.id === editAuthor.id ? response.data : author));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update author:', error);
            setError('Failed to update author.');
        }
    };

    const handleDeleteAuthor = async () => {
        try {
            await ApiService.deleteAuthor(deleteAuthor.id);
            setAuthors(prevAuthors => prevAuthors.filter(author => author.id !== deleteAuthor.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete author:', error);
            setError('Failed to delete author.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteAuthor) {
            await handleDeleteAuthor();
        } else if (editAuthor) {
            await handleUpdateAuthor(event);
        } else {
            await handleAddAuthor(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditAuthor(null);
        setDeleteAuthor(null);
        setNewAuthor({ name: '', email: '', biography: '', nationality: '', birthDate: '', deathDate: '' });
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Authors</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>+ Add Author</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteAuthor ? "Confirm Delete" : (editAuthor ? "Edit Author" : "Add New Author")}
            >
                {deleteAuthor ? (
                    <p>Are you sure you want to delete {deleteAuthor.name}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            placeholder="Enter name"
                            className="mb-2"
                            value={editAuthor ? editAuthor.name : newAuthor.name}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, name: e.target.value }) : setNewAuthor({ ...newAuthor, name: e.target.value })}
                        />
                        <Form.Control
                            type="email"
                            placeholder="Enter email"
                            className="mb-2"
                            value={editAuthor ? editAuthor.email : newAuthor.email}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, email: e.target.value }) : setNewAuthor({ ...newAuthor, email: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter biography"
                            className="mb-2"
                            value={editAuthor ? editAuthor.biography : newAuthor.biography}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, biography: e.target.value }) : setNewAuthor({ ...newAuthor, biography: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter nationality"
                            className="mb-2"
                            value={editAuthor ? editAuthor.nationality : newAuthor.nationality}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, nationality: e.target.value }) : setNewAuthor({ ...newAuthor, nationality: e.target.value })}
                        />
                        <Form.Control
                            type="date"
                            placeholder="Enter birth date"
                            className="mb-2"
                            value={editAuthor ? editAuthor.birthDate : newAuthor.birthDate}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, birthDate: e.target.value }) : setNewAuthor({ ...newAuthor, birthDate: e.target.value })}
                        />
                        <Form.Control
                            type="date"
                            placeholder="Enter death date"
                            className="mb-2"
                            value={editAuthor ? editAuthor.deathDate : newAuthor.deathDate}
                            onChange={e => editAuthor ? setEditAuthor({ ...editAuthor, deathDate: e.target.value }) : setNewAuthor({ ...newAuthor, deathDate: e.target.value })}
                        />
                    </>
                )}
            </ReusableModal>
            <Table className="table table-hover text-center mt-2">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Biography</th>
                    <th>Nationality</th>
                    <th>Birth Date</th>
                    <th>Death Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {authors.map(author => (
                    <tr key={author.id}>
                        <td>{author.id}</td>
                        <td>{author.name}</td>
                        <td>{author.email}</td>
                        <td>{author.biography}</td>
                        <td>{author.nationality}</td>
                        <td>{author.birthDate}</td>
                        <td>{author.deathDate}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm text-dark me-" onClick={() => handleEditClick(author)}>Edit</Button>{' '}
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeleteAuthor(author);
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

export default AuthorsPage;
