import React, { useEffect, useState } from 'react';
import Layout from '../components/layouts/Layout.jsx';
import ApiService from '../api/ApiService'; // Assumes you have a service to fetch data
import { Table, Spinner, Alert, Button } from 'react-bootstrap';
import { jwtDecode } from 'jwt-decode';

const UserOrdersPage = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUserOrders = async () => {
            try {
                const token = localStorage.getItem('token');
                const decodedToken = jwtDecode(token);
                const userId = decodedToken.userId;
                const response = await ApiService.getOrdersByUserId(userId);
                const orders = response.data;

                const bookCache = {};
                console.log(response.data);
                const ordersWithBooks = await Promise.all(
                    orders.map(async (order) => {
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

                        return {
                            ...order,
                            orderItems: orderItemsWithDetails,
                        };
                    })
                );

                setOrders(ordersWithBooks);
                setLoading(false);
            } catch (err) {
                setError('Failed to fetch orders.');
                setLoading(false);
            }
        };

        fetchUserOrders();
    }, []);

    const handleCancelOrder = async (orderId) => {
        try {
            // Update the order status to "Canceled"
            await ApiService.updateOrder(orderId, { orderStatus: 'Canceled' });

            // Refresh the orders list
            setOrders((prevOrders) =>
                prevOrders.map((order) =>
                    order.id === orderId ? { ...order, orderStatus: 'Canceled' } : order
                )
            );
        } catch (err) {
            setError('Failed to cancel the order.');
        }
    };

    if (loading) {
        return <Spinner animation="border" variant="primary" />;
    }

    if (error) {
        return <Alert variant="danger">{error}</Alert>;
    }

    return (
        <Layout>
            <div className="container mt-5">
                <h2>My Orders</h2>
                {orders.length === 0 ? (
                    <p>No orders found.</p>
                ) : (
                    <Table striped bordered hover>
                        <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Books</th>
                            <th>Total Price</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Actions</th>
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
                                <td>${order.totalPrice.toFixed(2)}</td>
                                <td>{new Date(order.date).toLocaleDateString()}</td>
                                <td>{order.orderStatus}</td>
                                <td>
                                    {order.orderStatus !== 'Canceled' && (
                                        <Button
                                            variant="danger"
                                            onClick={() => handleCancelOrder(order.id)}
                                        >
                                            Cancel Order
                                        </Button>
                                    )}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                )}
            </div>
        </Layout>
    );
};

export default UserOrdersPage;
