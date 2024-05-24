import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const UsersPage = () => {
    const [users, setUsers] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newUser, setNewUser] = useState({ username: '', email: '', password: '' });
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
            <h1>Manage Users</h1>
            <Button variant="primary mt-3" onClick={() => setShowModal(true)}>+ Add User</Button>
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
                            placeholder="Enter username"
                            value={editUser ? editUser.username : newUser.username}
                            onChange={e => editUser ? setEditUser({ ...editUser, username: e.target.value }) : setNewUser({ ...newUser, username: e.target.value })}
                        />
                        <Form.Control
                            type="email"
                            placeholder="Enter email"
                            value={editUser ? editUser.email : newUser.email}
                            onChange={e => editUser ? setEditUser({ ...editUser, email: e.target.value }) : setNewUser({ ...newUser, email: e.target.value })}
                        />
                        <Form.Control
                            type="password"
                            placeholder="Enter password"
                            value={editUser ? editUser.password : newUser.password}
                            onChange={e => editUser ? setEditUser({ ...editUser, password: e.target.value }) : setNewUser({ ...newUser, password: e.target.value })}
                        />
                    </>
                )}
            </ReusableModal>
            <Table className="table table-hover text-center mt-5">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.username}</td>
                        <td>{user.email}</td>
                        <td>
                            <Button variant="warning text-light me-4" onClick={() => handleEditClick(user)}>Edit</Button>{' '}
                            <Button variant="danger" onClick={() => {
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
