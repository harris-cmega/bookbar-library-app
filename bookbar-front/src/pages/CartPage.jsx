// src/pages/CartPage.js
import React, { useState, useEffect } from 'react';
import ApiService from '../api/ApiService';
import Layout from '../components/layouts/Layout';
import CartItemCard from '../components/cart/CartItemCard';
import MessageModal from '../components/MessageModal';
import { jwtDecode } from 'jwt-decode';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import '../index.css';

const stripePromise = loadStripe('pk_test_51PPiDJ02eNUXNagOi6eUCbdYWD4YGX2d1kk3F1xfrzVRMdfwZ85dSZC0KUbYOSWzf3eVj4cB3mVHP3EFO7jnftFC00zR33D15G');

const CartPage = () => {
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [totalPrice, setTotalPrice] = useState(0);
    const [userEmail, setUserEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    const fetchBookDetails = async (bookId) => {
        try {
            const response = await ApiService.getBookById(bookId);
            return response.data;
        } catch (error) {
            console.error('Error fetching book details:', error);
            throw error;
        }
    };

    useEffect(() => {
        const fetchCartItems = async () => {
            try {
                const token = localStorage.getItem('token');
                if (token) {
                    const decodedToken = jwtDecode(token);
                    const userId = decodedToken.userId;
                    const userEmail = decodedToken.email;
                    const firstName = decodedToken.firstName;
                    const lastName = decodedToken.lastName;

                    setUserEmail(userEmail);
                    setFirstName(firstName);
                    setLastName(lastName);

                    const response = await ApiService.getCartByUserId(userId);
                    const cartItems = response.data.cartItems || [];

                    const itemsWithBookDetails = await Promise.all(cartItems.map(async (item) => {
                        const bookDetails = await fetchBookDetails(item.bookId);
                        return { ...item, book: bookDetails };
                    }));

                    setCartItems(itemsWithBookDetails);
                    const total = itemsWithBookDetails.reduce((acc, item) => acc + item.book.price, 0);
                    setTotalPrice(total);
                } else {
                    setError('No token found');
                }
            } catch (error) {
                console.error('Error fetching cart items:', error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchCartItems();
    }, []);

    const handleRemoveCartItem = (cartItemId) => {
        setCartItems((prevItems) => {
            const updatedItems = prevItems.filter((item) => item.id !== cartItemId);
            const total = updatedItems.reduce((acc, item) => acc + item.book.price, 0);
            setTotalPrice(total);
            setModalMessage("Item removed!");
            setShowModal(true);
            return updatedItems;
        });
    };

    const handleCheckout = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/payment/secure/create-checkout-session', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`,
                },
                body: JSON.stringify({
                    userEmail: userEmail,
                    firstName: firstName,
                    lastName: lastName,
                    products: cartItems.map(item => item.book.title),
                }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                console.error('Error response from server:', errorData);
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            const stripe = await stripePromise;
            const { error } = await stripe.redirectToCheckout({
                sessionId: data.id,
            });

            if (error) {
                console.error('Stripe error:', error.message);
                setModalMessage(error.message);
                setShowModal(true);
            }
        } catch (error) {
            console.error('Error during checkout:', error);
            setModalMessage(error.message);
            setShowModal(true);
        }
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <Layout>
            <div className="container mt-5 mb-5 vh-100">
                <h1>Cart</h1>
                <div className="row">
                    {cartItems.length > 0 ? (
                        cartItems.map((item) => (
                            <div className="col-12 mb-4" key={item.id}>
                                <CartItemCard item={item} onRemove={handleRemoveCartItem} />
                            </div>
                        ))
                    ) : (
                        <p>Your cart is empty.</p>
                    )}
                </div>
                <div className="total-price">
                    {cartItems.length > 0 && (
                        <h3>Total Order: ${totalPrice.toFixed(2)}</h3>
                    )}
                </div>
                {cartItems.length > 0 && (
                    <button onClick={handleCheckout} className="btn btn-primary">
                        Proceed to Checkout
                    </button>
                )}
            </div>
            <MessageModal show={showModal} onClose={() => setShowModal(false)} message={modalMessage} />
        </Layout>
    );
};

export default CartPage;