import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';
import { UserIcon } from '@heroicons/react/24/outline';

const UsersPage = () => {
    const [users, setUsers] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newUser, setNewUser] = useState({ username: '', email: '', password: '', role: '', street: '', city: '', country: ''});
    const [editUser, setEditUser] = useState(null);
    const [deleteUser, setDeleteUser] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await ApiService.getUsers();
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
            setError('Error fetching users.');
        }
    };

    const handleAddUser = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createUser(newUser);
            setUsers(prevUsers => [...prevUsers, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add user:', error);
            setError('Failed to add user.');
        }
    };

    const handleEditClick = (user) => {
        setEditUser({
            id: user.id,
            username: user.username || '',
            email: user.email || '',
            street: user.street || '',
            city: user.city || '',
            country: user.country || '',
            password: ''
        });
        setShowModal(true);
    };

    const handleUpdateUser = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateUser(editUser.id, editUser);
            setUsers(prevUsers => prevUsers.map(user => user.id === editUser.id ? response.data : user));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update user:', error);
            setError('Failed to update user.');
        }
    };

    const handleDeleteUser = async () => {
        try {
            await ApiService.deleteUser(deleteUser.id);
            setUsers(prevUsers => prevUsers.filter(user => user.id !== deleteUser.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete user:', error);
            setError('Failed to delete user.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteUser) {
            await handleDeleteUser();
        } else if (editUser) {
            await handleUpdateUser(event);
        } else {
            await handleAddUser(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditUser(null);
        setDeleteUser(null);
        setNewUser({ username: '', email: '', password: '' });
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Users</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}><UserIcon
                    className="icon size-3 me-1"/> Add User</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteUser ? "Confirm Delete" : (editUser ? "Edit User" : "Add New User")}
            >
                {deleteUser ? (
                    <p>Are you sure you want to delete {deleteUser.username}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            className="mb-2"
                            placeholder="Enter username"
                            value={editUser ? editUser.username : newUser.username}
                            onChange={e => editUser ? setEditUser({ ...editUser, username: e.target.value }) : setNewUser({ ...newUser, username: e.target.value })}
                        />
                        <Form.Control
                            type="email"
                            className="mb-2"
                            placeholder="Enter email"
                            value={editUser ? editUser.email : newUser.email}
                            onChange={e => editUser ? setEditUser({ ...editUser, email: e.target.value }) : setNewUser({ ...newUser, email: e.target.value })}
                        />
                        <Form.Control
                            type="password"
                            className="mb-2"
                            placeholder="Enter password"
                            value={editUser ? editUser.password : newUser.password}
                            onChange={e => editUser ? setEditUser({ ...editUser, password: e.target.value }) : setNewUser({ ...newUser, password: e.target.value })}
                        />
                        <Form.Control
                            type="street"
                            className="mb-2"
                            placeholder="Enter street"
                            value={editUser ? editUser.street : newUser.street}
                            onChange={e => editUser ? setEditUser({ ...editUser, street: e.target.value }) : setNewUser({ ...newUser, street: e.target.value })}
                        />
                        <Form.Control
                            type="city"
                            className="mb-2"
                            placeholder="Enter city"
                            value={editUser ? editUser.city : newUser.city}
                            onChange={e => editUser ? setEditUser({ ...editUser, city: e.target.value }) : setNewUser({ ...newUser, city: e.target.value })}
                        />
                        <Form.Control
                            type="country"
                            className="mb-2"
                            placeholder="Enter country"
                            value={editUser ? editUser.country : newUser.country}
                            onChange={e => editUser ? setEditUser({ ...editUser, country: e.target.value }) : setNewUser({ ...newUser, country: e.target.value })}
                        />
                    </>
                )}
            </ReusableModal>
            <Table className="table table-hover text-center mt-2">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.username}</td>
                        <td>{user.email}</td>
                        <td>{user.role}</td>
                        <td>{user.street}</td>
                        <td>{user.city}</td>
                        <td>{user.country}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm text-dark me-2"
                                    onClick={() => handleEditClick(user)}>Edit</Button>{' '}
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeleteUser(user);
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

export default UsersPage;
