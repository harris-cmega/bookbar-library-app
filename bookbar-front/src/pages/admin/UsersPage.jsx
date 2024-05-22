import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Table } from 'react-bootstrap';

const UsersPage = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        ApiService.getUsers().then(response => {
            setUsers(response.data);
        }).catch(error => {
            console.error('Error fetching users:', error);
        });
    }, []);

    return (
        <div>
            <h1>Manage Users</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.username}</td>
                        <td>{user.email}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default UsersPage;
