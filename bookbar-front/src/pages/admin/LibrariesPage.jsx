import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const LibrariesPage = () => {
    const [libraries, setLibraries] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newLibrary, setNewLibrary] = useState({
        name: '',
        address: '',
        city: '',
        state: '',
        zip_code: '',
        phone: '',
        email: '',
        opening_hours: ''
    });
    const [editLibrary, setEditLibrary] = useState(null);
    const [deleteLibrary, setDeleteLibrary] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchLibraries();
    }, []);

    const fetchLibraries = async () => {
        try {
            const response = await ApiService.getLibraries();
            setLibraries(response.data);
        } catch (error) {
            console.error('Error fetching libraries:', error);
            setError('Error fetching libraries.');
        }
    };

    const handleAddLibrary = async (event) => {
        event.preventDefault();
        console.log('Adding library with data:', newLibrary); // Log request payload
        try {
            const response = await ApiService.createLibrary(newLibrary);
            setLibraries(prevLibraries => [...prevLibraries, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add library:', error.response ? error.response.data : error.message); // Log API error response
            setError('Failed to add library.');
        }
    };

    const handleEditClick = (library) => {
        setEditLibrary({
            id: library.id,
            name: library.name || '',
            address: library.address || '',
            city: library.city || '',
            state: library.state || '',
            zip_code: library.zip_code || '',
            phone: library.phone || '',
            email: library.email || '',
            opening_hours: library.opening_hours || ''
        });
        setShowModal(true);
    };

    const handleUpdateLibrary = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateLibrary(editLibrary.id, editLibrary);
            setLibraries(prevLibraries => prevLibraries.map(library => library.id === editLibrary.id ? response.data : library));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update library:', error.response ? error.response.data : error.message); // Log API error response
            setError('Failed to update library.');
        }
    };

    const handleDeleteLibrary = async () => {
        try {
            await ApiService.deleteLibrary(deleteLibrary.id);
            setLibraries(prevLibraries => prevLibraries.filter(library => library.id !== deleteLibrary.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete library:', error.response ? error.response.data : error.message); // Log API error response
            setError('Failed to delete library.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteLibrary) {
            await handleDeleteLibrary();
        } else if (editLibrary) {
            await handleUpdateLibrary(event);
        } else {
            await handleAddLibrary(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditLibrary(null);
        setDeleteLibrary(null);
        setNewLibrary({
            name: '',
            address: '',
            city: '',
            state: '',
            zip_code: '',
            phone: '',
            email: '',
            opening_hours: ''
        });
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        if (editLibrary) {
            setEditLibrary({ ...editLibrary, [name]: value });
        } else {
            setNewLibrary({ ...newLibrary, [name]: value });
        }
    };

    return (
        <div>
            <h1>Manage Libraries</h1>
            <Button variant="primary mt-3" onClick={() => setShowModal(true)}>+ Add Library</Button>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteLibrary ? "Confirm Delete" : (editLibrary ? "Edit Library" : "Add New Library")}
            >
                {deleteLibrary ? (
                    <p>Are you sure you want to delete {deleteLibrary.name}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            name="name"
                            placeholder="Enter name"
                            value={editLibrary ? editLibrary.name : newLibrary.name}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="address"
                            placeholder="Enter address"
                            value={editLibrary ? editLibrary.address : newLibrary.address}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="city"
                            placeholder="Enter city"
                            value={editLibrary ? editLibrary.city : newLibrary.city}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="state"
                            placeholder="Enter state"
                            value={editLibrary ? editLibrary.state : newLibrary.state}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="zip_code"
                            placeholder="Enter zip code"
                            value={editLibrary ? editLibrary.zip_code : newLibrary.zip_code}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="phone"
                            placeholder="Enter phone"
                            value={editLibrary ? editLibrary.phone : newLibrary.phone}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            value={editLibrary ? editLibrary.email : newLibrary.email}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="opening_hours"
                            placeholder="Enter opening hours"
                            value={editLibrary ? editLibrary.opening_hours : newLibrary.opening_hours}
                            onChange={handleInputChange}
                        />
                    </>
                )}
            </ReusableModal>
            <Table className="table table-hover text-center mt-5">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Opening Hours</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {libraries.map(library => (
                    <tr key={library.id}>
                        <td>{library.id}</td>
                        <td>{library.name}</td>
                        <td>{library.address}</td>
                        <td>{library.city}</td>
                        <td>{library.state}</td>
                        <td>{library.zip_code}</td>
                        <td>{library.phone}</td>
                        <td>{library.email}</td>
                        <td>{library.opening_hours}</td>
                        <td>
                            <Button variant="warning text-light me-4" onClick={() => handleEditClick(library)}>Edit</Button>{' '}
                            <Button variant="danger" onClick={() => {
                                setDeleteLibrary(library);
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

export default LibrariesPage;
