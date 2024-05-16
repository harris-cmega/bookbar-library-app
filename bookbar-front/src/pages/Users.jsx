import React, { useEffect, useState, useContext } from 'react';
import ApiService from '../api/ApiService';
import { AuthContext } from '../context/AuthContext';
import Layout from "../components/Layout.jsx";

const Users = () => {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);
    const { user } = useContext(AuthContext);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await ApiService.getUsers();
                setUsers(response.data);
            } catch (error) {
                setError(error.message);
                console.error('Failed to fetch users:', error);
            }
        };

        if (user) {
            console.log('User is authenticated:', user);
            fetchUsers();
        } else {
            console.log('User is not authenticated');
        }
    }, [user]);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <Layout>
            <h1>Users</h1>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>{user.username}</li>
                ))}
            </ul>
        </Layout>
    );
};

export default Users;
