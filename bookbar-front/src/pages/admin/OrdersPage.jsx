import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table, Spinner, Alert } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';

const OrdersPage = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [editOrder, setEditOrder] = useState(null);
    const [deleteOrder, setDeleteOrder] = useState(null);

    useEffect(() => {
        const fetchOrdersWithBooks = async () => {
            try {
                const response = await ApiService.getOrders();
                const orders = response.data;

                const bookCache = {};
                const userCache = {};

                const ordersWithBooks = await Promise.all(
                    orders.map(async (order) => {
                        // Fetch order items with book details
                        const orderItemsWithDetails = await Promise.all(
                            order.orderItems.map(async (item) => {
                                if (!bookCache[item.bookId]) {
                                    try {
                                        const bookResponse = await ApiService.getBookById(item.bookId);
                                        bookCache[item.bookId] = bookResponse.data;
                                    } catch (error) {
                                        console.error(`Error fetching book with id: ${item.bookId}`, error);
                                    }
                                }

                                return {
                                    ...item,
                                    bookTitle: bookCache[item.bookId]?.title || 'Unknown Title',
                                };
                            })
                        );

                        // Fetch user details if not cached
                        if (!userCache[order.userId]) {
                            try {
                                const userResponse = await ApiService.getUserById(order.userId);
                                userCache[order.userId] = userResponse.data.username;
                            } catch (error) {
                                console.error(`Error fetching user with id: ${order.userId}`, error);
                            }
                        }

                        return {
                            ...order,
                            orderItems: orderItemsWithDetails,
                            username: userCache[order.userId] || 'Unknown User',
                        };
                    })
                );

                setOrders(ordersWithBooks);
                setLoading(false);
            } catch (error) {
                setError('Error fetching orders.');
                setLoading(false);
            }
        };
        fetchOrdersWithBooks();
    }, []);

    const handleStatusChange = async (orderId, newStatus) => {
        try {
            const orderToUpdate = orders.find(order => order.id === orderId);

            const updatedOrder = {
                id: orderToUpdate.id,
                totalPrice: orderToUpdate.totalPrice,
                orderStatus: newStatus, // Updated status
                userId: orderToUpdate.userId, // Or however you're referencing the user
                orderItems: orderToUpdate.orderItems, // Include the order items if necessary
            };

            await ApiService.updateOrder(orderId, updatedOrder);

            setOrders(prevOrders =>
                prevOrders.map(order =>
                    order.id === orderId ? { ...order, status: newStatus } : order
                )
            );
            window.location.reload();
        } catch (error) {
            setError('Failed to update order status.');
        }
    };

    const handleDeleteClick = (order) => {
        setDeleteOrder(order);
        setShowModal(true);
    };

    const handleDeleteOrder = async () => {
        try {
            await ApiService.deleteOrder(deleteOrder.id);
            setOrders(prevOrders => prevOrders.filter(order => order.id !== deleteOrder.id));
            setDeleteOrder(null);
            handleCloseModal();
        } catch (error) {
            setError('Error deleting order.');
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setDeleteOrder(null);
    };

    if (loading) return <Spinner animation="border" />;
    if (error) return <Alert variant="danger">{error}</Alert>;

    return (
        <div>
            <Table className="table table-hover text-center mt-2">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Books</th>
                    <th>Total Price</th>
                    <th>Date</th>
                    <th>Username</th> {/* New column */}
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {orders.map((order) => (
                    <tr key={order.id}>
                        <td>{order.id}</td>
                        <td>
                            {order.orderItems.map((item) => (
                                <div key={item.bookId} style={{ marginBottom: '10px' }}>
                                    {item.bookTitle}
                                </div>
                            ))}
                        </td>
                        <td>${order.totalPrice}</td>
                        <td>{new Date(order.date).toLocaleString()}</td>
                        <td>{order.username}</td> {/* Display username */}
                        <td>
                            <Form.Select
                                value={order.orderStatus || ''}
                                onChange={(e) => handleStatusChange(order.id, e.target.value)}
                            >
                                <option value=" ">Select</option>
                                <option value="Confirmed">Confirmed</option>
                                <option value="On Its Way">On Its Way</option>
                                <option value="Delivered">Delivered</option>
                                <option value="Denied">Denied</option>
                                <option value="Delayed">Delayed</option>
                                <option value="Canceled">Canceled</option>
                            </Form.Select>
                        </td>
                        <td>
                            <Button variant="danger" size="sm" onClick={() => handleDeleteClick(order)}>
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleDeleteOrder}
                title="Confirm Delete"
            >
                <p>Are you sure you want to delete {deleteOrder?.id}?</p>
            </ReusableModal>
        </div>
    );
};

export default OrdersPage;
