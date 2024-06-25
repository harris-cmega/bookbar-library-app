import React from 'react';
import ApiService from "../../api/ApiService";
import {jwtDecode} from "jwt-decode";

const CartItemCard = ({ item, onRemove }) => {
    const { book, id } = item;
    const imageUrl = book.image ? `http://localhost:8080/${book.image}` : `http://localhost:8080/public/ph/placeholder.png`;

    const handleRemove = async () => {
        try {
            const token = localStorage.getItem('token');
            const decodedToken = jwtDecode(token);
            const userId = decodedToken.userId;
            await ApiService.removeItemFromCart(userId, id);
            onRemove(id);
        } catch (error) {
            console.error('Error removing item from cart:', error);
            alert('Failed to remove item from cart');
        }
    };

    return (
        <div className="cart-card mb-4">
            <div className="cart-card-image">
                <img
                    src={imageUrl}
                    className="card-img-top"
                    alt={book.title}
                />
            </div>
            <div className="cart-card-body">
                <h5 className="cart-card-title">{book.title}</h5>
                <p className="cart-card-text">Author: {book.author_name}</p>
                <p className="cart-card-price">Price: ${book.price}</p>
                <button
                    className="btn btn-danger"
                    onClick={handleRemove}
                >
                    Remove
                </button>
            </div>
        </div>
    );
};

export default CartItemCard;
