import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const PublishersPage = () => {
    const [publishers, setPublishers] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newPublisher, setnewPublisher] = useState({ id: '', name:'', address:'', city:'', state:'', phone:'', email:'', website:''});
    const [editPublisher, setEditPublisher] = useState(null);
    const [deletePublisher, setDeletePublisher] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchPublishers();
    }, []);

    const fetchPublishers = async () => {
        try {
            const response = await ApiService.getPublishers();
            setPublishers(response.data);
        } catch (error) {
            console.error('Error fetching publishers:', error);
            setError('Error fetching publishers.');
        }
    };

    const handleAddPublisher = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createPublisher(newPublisher);
            setPublishers(prevPublishers => [...prevPublishers, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add publisher:', error);
            setError('Failed to add publisher.');
        }
    };

    const handleEditClick = (publisher) => {
        setEditPublisher({
            id: publisher.id,
            name: publisher.name || '',
            address: publisher.address || '',
            city: publisher.city || '',
            state: publisher.state || '',
            phone: publisher.phone || '',
            email: publisher.email || '',
            website: publisher.website || ''
        });
        setShowModal(true);
    };

    const handleUpdatePublisher = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updatePublisher(editPublisher.id, editPublisher);
            setPublishers(prevPublishers => prevPublishers.map(publisher => publisher.id === editPublisher.id ? response.data : publisher));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update publisher:', error);
            setError('Failed to update publisher.');
        }
    };

    const handleDeletePublisher = async () => {
        try {
            await ApiService.deletePublisher(deletePublisher.id);
            setPublishers(prevPublishers => prevPublishers.filter(publisher => publisher.id !== deletePublisher.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete publisher:', error);
            setError('Failed to delete publisher.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deletePublisher) {
            await handleDeletePublisher();
        } else if (editPublisher) {
            await handleUpdatePublisher(event);
        } else {
            await handleAddPublisher(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditPublisher(null);
        setDeletePublisher(null);
        setnewPublisher({name: '', address: '', city: '', state: '', phone: '', email: '', website: ''});
    };

    useEffect(() => {
        ApiService.getPublishers().then(response => {
            setPublishers(response.data);
        }).catch(error => {
            console.error('Error fetching publishers:', error);
        });
    }, []);

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Publishers</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>+ Add Publisher</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deletePublisher ? "Confirm Delete" : (editPublisher ? "Edit Publisher" : "Add New Publisher")}
            >
                {deletePublisher ? (
                    <p>Are you sure you want to delete {deletePublisher.name}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter name"
                            value={editPublisher ? editPublisher.name : newPublisher.name}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, name: e.target.value }) : setnewPublisher({ ...newPublisher, name: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter address"
                            value={editPublisher ? editPublisher.address : newPublisher.address}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, address: e.target.value }) : setnewPublisher({ ...newPublisher, address: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter city"
                            value={editPublisher ? editPublisher.city : newPublisher.city}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, city: e.target.value }) : setnewPublisher({ ...newPublisher, city: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter state"
                            value={editPublisher ? editPublisher.state : newPublisher.state}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, state: e.target.value }) : setnewPublisher({ ...newPublisher, state: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter phone"
                            value={editPublisher ? editPublisher.phone : newPublisher.phone}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, phone: e.target.value }) : setnewPublisher({ ...newPublisher, phone: e.target.value })}
                        />
                        <Form.Control
                            type="email"
                            className="mb-2"
                            placeholder="Enter email"
                            value={editPublisher ? editPublisher.email : newPublisher.email}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, email: e.target.value }) : setnewPublisher({ ...newPublisher, email: e.target.value })}
                        />
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter website"
                            value={editPublisher ? editPublisher.website : newPublisher.website}
                            onChange={e => editPublisher ? setEditPublisher({ ...editPublisher, website: e.target.value }) : setnewPublisher({ ...newPublisher, website: e.target.value })}
                        />
                    </>
                )}
            </ReusableModal>
            <Table hover center className="mt-2 text-center">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Website</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {publishers.map(publisher => (
                    <tr key={publisher.id}>
                        <td>{publisher.id}</td>
                        <td>{publisher.name}</td>
                        <td>{publisher.address}</td>
                        <td>{publisher.city}</td>
                        <td>{publisher.state}</td>
                        <td>{publisher.phone}</td>
                        <td>{publisher.email}</td>
                        <td>{publisher.website}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm text-dark me-2" onClick={() => handleEditClick(publisher)}>Edit</Button>{' '}
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeletePublisher(publisher);
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

export default PublishersPage;
