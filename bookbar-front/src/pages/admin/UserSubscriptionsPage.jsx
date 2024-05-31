import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const UserSubscriptionsPage = () => {
    const [userSubscriptions, setUserSubscriptions] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newUserSubscription, setNewUserSubscription] = useState({ userId: '', subscriptionId: '', startDate: '', endDate: '' });
    const [editUserSubscription, setEditUserSubscription] = useState(null);
    const [deleteUserSubscription, setDeleteUserSubscription] = useState(null);
    const [error, setError] = useState('');
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchUserSubscriptions();
        fetchUsers();
    }, []);

    const fetchUserSubscriptions = async () => {
        try {
            const response = await ApiService.getUserSubscriptions();
            setUserSubscriptions(response.data);
        } catch (error) {
            console.error('Error fetching user subscriptions:', error);
            setError('Error fetching user subscriptions.');
        }
    };

    const fetchUsers = async () => {
        try {
            const response = await ApiService.getUsers();
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
            setError('Error fetching users.');
        }
    };

    const handleAddUserSubscription = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createSubscriptionForUser(newUserSubscription.userId, newUserSubscription);
            setUserSubscriptions(prevUserSubscriptions => [...prevUserSubscriptions, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add subscription:', error);
            setError('Failed to add subscription.');
        }
    };

    const handleEditClick = (userSubscription) => {
        setEditUserSubscription({
            id: userSubscription.id,
            userId: userSubscription.userId || '',
            subscriptionId: userSubscription.subscriptionId || '',
            startDate: userSubscription.startDate || '',
            endDate: userSubscription.endDate || ''
        });
        setShowModal(true);
    };
    
    const handleUpdateUserSubscription = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateUserSubscription(editUserSubscription.id, editUserSubscription);
            setUserSubscriptions(prevUserSubscriptions => prevUserSubscriptions.map(userSubscription => userSubscription.id === editUserSubscription.id ? response.data : userSubscription));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update subscription:', error);
            setError('Failed to update subscription.');
        }
    };

    const handleDeleteUserSubscription = async () => {
        try {
            await ApiService.deleteUserSubscription(deleteUserSubscription.id);
            setUserSubscriptions(prevUserSubscriptions => prevUserSubscriptions.filter(userSubscription => userSubscription.id !== deleteUserSubscription.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete subscription:', error);
            setError('Failed to delete subscription.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteUserSubscription) {
            await handleDeleteUserSubscription();
        } else if (editUserSubscription) {
            await handleUpdateUserSubscription(event);
        } else {
            await handleAddUserSubscription(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditUserSubscription(null);
        setDeleteUserSubscription(null);
        setNewUserSubscription({ userId: '', subscriptionId: '', startDate: '', endDate: '' });
    };

    const handleStartDateChange = (e) => {
        const selectedDate = new Date(e.target.value);
        const endDate = new Date(selectedDate.setDate(selectedDate.getDate() + 30)).toISOString().slice(0, 16);
        
        if (editUserSubscription) {
            setEditUserSubscription({ ...editUserSubscription, startDate: e.target.value, endDate });
        } else {
            setNewUserSubscription({ ...newUserSubscription, startDate: e.target.value, endDate });
        }
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage User Subscriptions</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>+ Add Subscription</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteUserSubscription ? "Confirm Delete" : (editUserSubscription ? "Edit Subscription" : "Add New Subscription")}
            >
                {deleteUserSubscription ? (
                    <p>Are you sure you want to delete this subscription?</p>
                ) : (
                    <>
                        <Form.Select 
                            className="mb-2" 
                            value={editUserSubscription ? editUserSubscription.userId : newUserSubscription.userId} 
                            onChange={e => 
                                editUserSubscription 
                                    ? setEditUserSubscription({ ...editUserSubscription, userId: e.target.value }) 
                                    : setNewUserSubscription({ ...newUserSubscription, userId: e.target.value })
                            }
                        >
                            <option value="">Select User</option>
                            {users.map(user => (
                                <option key={user.id} value={user.id}>{user.username}</option>
                            ))}
                        </Form.Select>

                        <Form.Select
                            className="mb-2"
                            value={editUserSubscription ? editUserSubscription.subscriptionId : newUserSubscription.subscriptionId}
                            onChange={e => 
                                editUserSubscription 
                                    ? setEditUserSubscription({ ...editUserSubscription, subscriptionId: e.target.value }) 
                                    : setNewUserSubscription({ ...newUserSubscription, subscriptionId: e.target.value })
                            }
                        >
                            <option value="">Select Subscription</option>
                            <option value="1">Classic</option>
                            <option value="2">Premium</option>
                            <option value="3">Special</option>
                        </Form.Select>

                        <Form.Control 
                            type="datetime-local" 
                            className="mb-2" 
                            value={editUserSubscription ? editUserSubscription.startDate : newUserSubscription.startDate} 
                            onChange={handleStartDateChange}
                        />
                    </>
                )}
            </ReusableModal>
            <Table hover className="mt-2 text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Subscription ID</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                {userSubscriptions.map(userSubscription => (
                    <tr key={userSubscription.id}>
                        <td>{userSubscription.id}</td>
                        <td>{userSubscription.userId}</td>
                        <td>{userSubscription.subscriptionId}</td>
                        <td>{userSubscription.startDate}</td>
                        <td>{userSubscription.endDate}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm" className="me-2" onClick={() => handleEditClick(userSubscription)}>Edit</Button>
                            <Button variant="danger btn-sm" onClick={() => { setDeleteUserSubscription(userSubscription); setShowModal(true); }}>Delete</Button>
                        </td>
                    </tr>
                ))}
               </tbody>
            </Table>
        </div>
    );
};

export default UserSubscriptionsPage;