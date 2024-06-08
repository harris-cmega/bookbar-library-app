import React, { useState, useEffect } from 'react';
import ApiService from '../api/ApiService';
import Layout from '../components/layouts/Layout';
import CartItemCard from '../components/cart/CartItemCard';
import {jwtDecode} from 'jwt-decode';

const CartPage = () => {
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

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

                    const response = await ApiService.getCartByUserId(userId);
                    const cartItems = response.data.cartItems || [];

                    const itemsWithBookDetails = await Promise.all(cartItems.map(async (item) => {
                        const bookDetails = await fetchBookDetails(item.bookId);
                        return { ...item, book: bookDetails };
                    }));

                    setCartItems(itemsWithBookDetails);
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
        setCartItems((prevItems) => prevItems.filter((item) => item.id !== cartItemId));
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <Layout>
            <div className="container mt-5 mb-5">
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
            </div>
        </Layout>
    );
};

export default CartPage;
